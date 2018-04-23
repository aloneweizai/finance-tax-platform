package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.Message;
import com.abc12366.bangbang.model.MessageSendBo;
import com.abc12366.bangbang.model.bo.MessageBO;

import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/18.
 */
public interface MessageSendUtil {


    /**
     * 需要验证token的发送
     */
    MessageBO sendMessage(Message message, HttpServletRequest request);

    /**
     * 不需要验证token的发送
     */
    MessageBO sendMessage(Message message);

    String sendMsg(String url, HttpMethod method, Map<String, Object> map);
    
    String sendMsgBySubscriptions(MessageSendBo sendBo, HttpServletRequest request);

}
