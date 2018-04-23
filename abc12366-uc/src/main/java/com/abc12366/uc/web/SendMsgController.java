package com.abc12366.uc.web;

import com.abc12366.gateway.util.Constant;
import com.abc12366.uc.model.MessageSendBo;
import com.abc12366.uc.service.IMsgSendService;
import com.abc12366.uc.service.IMsgSendV2service;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前段三类消息发送接口
 * @author zhushuai 2017-11-13
 *
 */
@Controller
@RequestMapping(path = "/sendmsg", headers = Constant.VERSION_HEAD + "=" + Constant.VERSION_1)
public class SendMsgController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SendMsgController.class);
	
	 @Autowired
	 private IMsgSendService msgSendService;
	 
	 @Autowired
	 private IMsgSendV2service msgSendV2Service;
     
	 @SuppressWarnings("rawtypes")
	 @PostMapping("/byqd")
	 public ResponseEntity sendByQd(@RequestBody MessageSendBo sendBo){
		  LOGGER.info("接收到前端消息:"+JSONObject.toJSONString(sendBo));
		  return msgSendService.sendXtxx(sendBo);
	 }
	 
	 @SuppressWarnings("rawtypes")
	 @PostMapping("/byqdbatch")
	 public ResponseEntity sendByQdbatch(@RequestBody MessageSendBo sendBo){
		  LOGGER.info("接收到前端消息:"+JSONObject.toJSONString(sendBo));
		  return msgSendService.sendXtxxbatch(sendBo);
	 }
	 
	 @SuppressWarnings("rawtypes")
	 @PostMapping("/v2")
	 public ResponseEntity sendMsgV2(@RequestBody MessageSendBo sendBo){
		  LOGGER.info("接收到消息:"+JSONObject.toJSONString(sendBo));
		  return msgSendV2Service.sendMsgV2(sendBo);
	 }
	 
}
