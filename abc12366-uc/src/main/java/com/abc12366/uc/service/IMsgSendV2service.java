package com.abc12366.uc.service;

import org.springframework.http.ResponseEntity;

import com.abc12366.uc.model.MessageSendBo;

/**
 * 新版本消息发送接口  V2
 * @author Administrator
 *
 */
public interface IMsgSendV2service {
    /**
     * 新版消息发送  
     * @param messageSendBo
     * @return
     */
	@SuppressWarnings("rawtypes")
	public ResponseEntity sendMsgV2(MessageSendBo messageSendBo);
}
