package com.abc12366.message.service.impl;

import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.MessageConstant;
import com.abc12366.gateway.util.RestTemplateUtil;
import com.abc12366.message.model.MessageSendLog;
import com.abc12366.message.model.bo.MobileMsgBO;
import com.abc12366.message.model.bo.UpyunErrorBO;
import com.abc12366.message.model.bo.UpyunMessageResponse;
import com.abc12366.message.service.SendMobileMsgService;
import com.abc12366.message.service.SendMsgLogService;
import com.abc12366.message.util.WeightFactorProduceStrategy;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-24
 * Time: 9:59
 */
@Service
public class SendMobileMsgServiceImpl implements SendMobileMsgService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendMobileMsgServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SendMsgLogService sendMsgLogService;


    @Autowired
    private com.abc12366.message.service.MobileVerifyCodeService mobileVerifyCodeService;

    @Override
    public void sendMsg(MobileMsgBO mobileMsgBO) {
        String channel = WeightFactorProduceStrategy.getInstance().getPartitionIdForTopic();
        if (MessageConstant.MSG_CHANNEL_YOUPAI.equals(channel)) {
            sendMsgByUppyun(mobileMsgBO);
        } else {
            mobileVerifyCodeService.sendAliYunMsg(
                    mobileMsgBO.getPhone(),
                    MessageConstant.MOBILE_MSG_BUSI_TYPE,
                    mobileMsgBO.getVars().get(0).getVar(),
                    MessageConstant.ALIYUNTEMP_DXTZ);
        }

    }

    @Override
    public void sendMsgByUppyun(MobileMsgBO mobileMsgBO) {
        //发送通知类短信接口地址
        String url = SpringCtxHolder.getProperty("message.upyun.send.url") + "/messages";
        //调用又拍接口请求头设置
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");
        httpHeaders.add("Authorization", SpringCtxHolder.getProperty("message.upyun.auth"));
        //调用又拍接口请求体设置
        LinkedMultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("mobile", mobileMsgBO.getPhone());
        requestBody.add("template_id", mobileMsgBO.getTemplateId());
        //将模板中的内容拼接成入参
        String vars = "";
        if (!StringUtils.isEmpty(mobileMsgBO.getVars()) && mobileMsgBO.getVars().size() > 0) {
            for (int i = 0; i < mobileMsgBO.getVars().size(); i++) {
                vars += "|";
                vars += mobileMsgBO.getVars().get(i).getVar();
            }
            vars = vars.substring(1, vars.length());
        }

        requestBody.add("vars", vars);
        HttpEntity entity = new HttpEntity(requestBody, httpHeaders);
        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        } catch (Exception e) {
            //记日志
            MessageSendLog sendLog = new MessageSendLog(MessageConstant.MSG_CHANNEL_YOUPAI, mobileMsgBO.getPhone(),
                    MessageConstant.MOBILE_MSG_BUSI_TYPE,
                    vars, MessageConstant.SEND_MSG_STATUS_FAIL, MessageConstant.SEND_MSG_CHANNEL_ERROR_CODE,
                    MessageConstant.SEND_MSG_CHANNEL_ERROR_YOUPAI);
            sendMsgLogService.insert(sendLog);
            LOGGER.info("发送业务通知短信失败，号码：{}，通道：又拍", vars);
            throw new ServiceException(4204);
        }
        if (RestTemplateUtil.isExchangeSuccessful(responseEntity)) {
            try {
                UpyunMessageResponse umr = JSON.parseObject(String.valueOf(responseEntity.getBody()),
                        UpyunMessageResponse.class);
                LOGGER.info("{}", umr);
                //记日志
                MessageSendLog sendLog = new MessageSendLog(MessageConstant.MSG_CHANNEL_YOUPAI, mobileMsgBO.getPhone
                        (), MessageConstant.MOBILE_MSG_BUSI_TYPE,
                        vars, MessageConstant.SEND_MSG_STATUS_SUCCESS, MessageConstant.SEND_MSG_SUCCESS_CODE,
                        MessageConstant.SEND_MSG_SUCCESS_CONTENT);
                sendMsgLogService.insert(sendLog);
            } catch (Exception e) {
                UpyunErrorBO response = JSON.parseObject(String.valueOf(responseEntity.getBody()), UpyunErrorBO.class);
                //记日志
                MessageSendLog sendLog = new MessageSendLog(MessageConstant.MSG_CHANNEL_YOUPAI, mobileMsgBO.getPhone
                        (), MessageConstant.MOBILE_MSG_BUSI_TYPE,
                        vars, MessageConstant.SEND_MSG_STATUS_FAIL, response.getError_code(), response.getMessage());
                sendMsgLogService.insert(sendLog);
            }
        }
    }
}
