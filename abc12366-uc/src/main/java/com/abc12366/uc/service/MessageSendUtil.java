package com.abc12366.uc.service;

import com.abc12366.uc.model.Message;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.MessageBO;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    
    MessageBO sendMessage(Message message,String accessToken);
    
    /**
     * 短信发送
     * @param phone
     * @param accessToken
     */
	void sendPhoneMessage(String phone,String templateId,  List<Map<String,String>> list, String accessToken);


    /**
     * 订单和发票发送消息公共方法
     * @param request
     * @param user
     * @param message
     * @param map
     * @param templateId
     */
    void sendMsg(HttpServletRequest request, User user, Message message,Map<String, String> map,String templateId);

    /**
     * 发送短信通知方法
     * @param request
     * @param content
     * @param phone
     */
    void sendPhoneMessage(HttpServletRequest request, String content, String phone);

}
