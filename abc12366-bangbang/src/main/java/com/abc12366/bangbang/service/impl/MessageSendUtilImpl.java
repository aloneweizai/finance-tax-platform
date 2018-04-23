package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.model.Message;
import com.abc12366.bangbang.model.MessageSendBo;
import com.abc12366.bangbang.model.bo.MessageBO;
import com.abc12366.bangbang.service.MessageSendUtil;
import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.RestTemplateUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/18.
 */
@Service("messageSendUtil")
public class MessageSendUtilImpl implements MessageSendUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSendUtilImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplateUtil restTemplateUtil;
    /**
     * 需要验证token的发送
     */
    @Override
    public MessageBO sendMessage(Message message, HttpServletRequest request) {
        LOGGER.info("{}:{}", message, request);
        String url = SpringCtxHolder.getProperty("abc12366.message.url") + "/business";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", message.getUserId());
        map.put("businessId", message.getBusinessId());
        map.put("content", message.getContent());
        map.put("status", "1");
        map.put("type", message.getType());

        String responseStr;
        try {
            responseStr = restTemplateUtil.exchange(url, HttpMethod.POST, map, request);
        } catch (Exception e) {
            throw new ServiceException(4821);
        }
        MessageBO response = null;
        if (!StringUtils.isEmpty(responseStr)) {
            response = JSON.parseObject(responseStr, MessageBO.class);
        }
        return response;
    }

    /**
     * 不需要验证token的发送
     */
    @Override
    public MessageBO sendMessage(Message message) {
        LOGGER.info("{}:{}", message);
        String url = SpringCtxHolder.getProperty("abc12366.message.url") + "/business";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", message.getUserId());
        map.put("businessId", message.getBusinessId());
        map.put("content", message.getContent());
        map.put("status", "1");
        map.put("type", message.getType());

        String responseStr;
        try {
            responseStr = sendMsg(url, HttpMethod.POST, map);
        } catch (Exception e) {
            throw new ServiceException(4821);
        }
        MessageBO response = null;
        if (!StringUtils.isEmpty(responseStr)) {
            response = JSON.parseObject(responseStr, MessageBO.class);
        }
        return response;
    }

    @Override
    public String sendMsg(String url, HttpMethod method, Map<String, Object> map) {
        //请求头设置
        HttpHeaders httpHeaders = new HttpHeaders();

        HttpEntity requestEntity = new HttpEntity(map, httpHeaders);
        LOGGER.info("Request: {}, {}", url, requestEntity);
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(url, method, requestEntity, String.class);
        } catch (RestClientException e) {
            throw new ServiceException("0000", "调用接口异常，地址：" + url);
        }
        LOGGER.info("Response: {}, {}", url, responseEntity);
        return responseEntity != null ? responseEntity.getBody() : null;
    }

	@Override
	public String sendMsgBySubscriptions(MessageSendBo sendBo, HttpServletRequest request) {
		LOGGER.info("{}:{}", sendBo, request);
        String url = SpringCtxHolder.getProperty("abc12366.uc.url") + "/sendmsg/v2";

        String responseStr;
        try {
            responseStr = restTemplateUtil.exchange(url, HttpMethod.POST, sendBo, request);
        } catch (Exception e) {
            throw new ServiceException(4821);
        }
        return responseStr;
	}
}
