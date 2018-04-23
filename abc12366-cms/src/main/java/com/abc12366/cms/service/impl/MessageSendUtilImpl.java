package com.abc12366.cms.service.impl;

import com.abc12366.cms.model.MessageSendBo;
import com.abc12366.cms.service.MessageSendUtil;
import com.abc12366.gateway.component.SpringCtxHolder;
import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.RestTemplateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Administrator on 2017/9/18.
 */
@Service("messageSendUtil")
public class MessageSendUtilImpl implements MessageSendUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSendUtilImpl.class);


    @Autowired
    private RestTemplateUtil restTemplateUtil;
   

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
