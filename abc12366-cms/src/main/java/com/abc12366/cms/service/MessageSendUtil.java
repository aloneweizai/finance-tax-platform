package com.abc12366.cms.service;

import com.abc12366.cms.model.MessageSendBo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/9/18.
 */
public interface MessageSendUtil {
    
    String sendMsgBySubscriptions(MessageSendBo sendBo, HttpServletRequest request);

}
