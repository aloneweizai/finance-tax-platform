package com.abc12366.uc.service.impl;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.RestTemplateUtil;
import com.abc12366.uc.model.Message;
import com.abc12366.uc.model.bo.MessageBO;
import com.abc12366.uc.model.bo.MessageListBO;
import com.abc12366.uc.service.MessageService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-09
 * Time: 11:41
 */
@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Override
    public MessageListBO selectList(String type, String busiType,String status,int page, int size, HttpServletRequest request) throws IOException {
        LOGGER.info("{},{}:{}:{}", type, page, size, request);
        String url = SpringCtxHolder.getProperty("abc12366.message.url") + "/business?";
        if (type != null && !"".equals(type)) {
            url = url + "type=" + type;
        }
        if(!StringUtils.isEmpty(busiType)){
            url = url + "&busiType=" + busiType;
        }
        if(!StringUtils.isEmpty(status)){
            url = url + "&status=" + status;
        }
        if (!StringUtils.isEmpty(request.getAttribute("page"))) {
            url += "&page=" + request.getAttribute("page");
            if (!StringUtils.isEmpty(request.getAttribute("size"))) {
                url += "&size=" + request.getAttribute("size");
            }
        }
        String responseStr;
        try {
            responseStr = restTemplateUtil.send(url, HttpMethod.GET, request);
        } catch (Exception e) {
            throw new ServiceException(4822);
        }
        MessageListBO response = null;
        if (!StringUtils.isEmpty(responseStr)) {
            response = JSON.parseObject(responseStr, MessageListBO.class);
        }
        return response;
    }

    @Override
    public MessageBO insert(Message message, HttpServletRequest request) throws IOException {
        LOGGER.info("{}:{}", message, request);

        String url = SpringCtxHolder.getProperty("abc12366.message.url") + "/business";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", message.getUserId());
        map.put("businessId", message.getBusinessId());
        map.put("content", message.getContent());
        map.put("status", "1");
        map.put("type", message.getType());
        map.put("busiType", message.getBusiType());

        String responseStr;
        try {
            responseStr = restTemplateUtil.exchange(url, HttpMethod.POST, map, request);
        } catch (Exception e) {
            throw new ServiceException(4822);
        }
        MessageBO response = null;
        if (!StringUtils.isEmpty(responseStr)) {
            response = JSON.parseObject(responseStr, MessageBO.class);
        }
        return response;
    }

    @Override
    public MessageBO selectOne(String id, HttpServletRequest request) throws IOException {
        String url = SpringCtxHolder.getProperty("abc12366.message.url") + "/business/" + id;

        String responseStr;
        try {
            responseStr = restTemplateUtil.send(url, HttpMethod.GET, request);
        } catch (Exception e) {
            throw new ServiceException(4822);
        }
        MessageBO response = null;
        if (!StringUtils.isEmpty(responseStr)) {
            response = JSON.parseObject(responseStr, MessageBO.class);
        }
        return response;
    }

    @Override
    public MessageBO update(String id, HttpServletRequest request) throws IOException {
        String url = SpringCtxHolder.getProperty("abc12366.message.url") + "/business/" + id;

        String responseStr;
        try {
            responseStr = restTemplateUtil.send(url, HttpMethod.PUT, request);
        } catch (Exception e) {
            throw new ServiceException(4822);
        }
        MessageBO response = null;
        if (!StringUtils.isEmpty(responseStr)) {
            response = JSON.parseObject(responseStr, MessageBO.class);
        }
        return response;
    }

    @Override
    public MessageBO delete(String id, HttpServletRequest request) throws IOException {
        String url = SpringCtxHolder.getProperty("abc12366.message.url") + "/business/" + id;

        String responseStr;
        try {
            responseStr = restTemplateUtil.send(url, HttpMethod.DELETE, request);
        } catch (Exception e) {
            throw new ServiceException(4822);
        }
        MessageBO response = null;
        if (!StringUtils.isEmpty(responseStr)) {
            response = JSON.parseObject(responseStr, MessageBO.class);
        }
        return response;
    }
}
