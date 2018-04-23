package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.model.bo.AppBO;
import com.abc12366.gateway.service.AppService;
import com.abc12366.gateway.util.MessageConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db2.VipPrivilegeLevelRoMapper;
import com.abc12366.uc.model.Message;
import com.abc12366.uc.model.MessageSendBo;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.VipPrivilegeLevelBO;
import com.abc12366.uc.service.IMsgSendService;
import com.abc12366.uc.service.IWxTemplateService;
import com.abc12366.uc.service.MessageSendUtil;
import com.abc12366.uc.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("msgSendService")
public class MsgSendServiceImpl implements IMsgSendService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MsgSendServiceImpl.class);

    @Autowired
    private AppService appService;

    @Autowired
    private MessageSendUtil messageSendUtil;

    @Autowired
    private IWxTemplateService templateService;

    @Autowired
    private VipPrivilegeLevelRoMapper vipPrivilegeLevelRoMapper;

    @Autowired
    private UserService userService;

    @SuppressWarnings("rawtypes")
	@Override
    public ResponseEntity sendMsgByQq(MessageSendBo messageSendBo) {
        User user = userService.selectUser(messageSendBo.getUserId());
        if (user != null) {

            LOGGER.info("发送信息用户信息:" + JSONObject.toJSONString(user));

            VipPrivilegeLevelBO obj = new VipPrivilegeLevelBO();
            obj.setLevelId(user.getVipLevel());
            obj.setPrivilegeId(MessageConstant.YWTX_CODE);
            VipPrivilegeLevelBO findObj = vipPrivilegeLevelRoMapper.selectLevelIdPrivilegeId(obj);

            //获取运营管理系统accessToken
            AppBO appBO=appService.selectByName("abc12366-admin");
            Date lastRest=appBO.getLastResetTokenTime();
            if(lastRest.before(new Date())){
            	appBO.setLastResetTokenTime(DateUtils.addHours(new Date(), 2));
            	appService.update(appBO);
            }       
            String accessToken = appBO.getAccessToken();
            LOGGER.info("获取运营管理系统accessToken:" + accessToken);

            //查看业务提醒是否启用
            if (findObj != null && findObj.getStatus()) {
                LOGGER.info("用户:" + user.getId() + ",提醒：" + messageSendBo.getWebMsg());

                //普通消息
                if (messageSendBo.getWebMsg() != null) {
                    Message message = new Message();
                    message.setBusinessId(StringUtils.isEmpty(messageSendBo.getBusinessId()) ? user.getId() :
                            messageSendBo.getBusinessId());
                    message.setBusiType(StringUtils.isEmpty(messageSendBo.getBusiType()) ? MessageConstant.XTTX :
                            messageSendBo.getBusiType());
                    message.setType(MessageConstant.SYS_MESSAGE);
                    message.setContent(messageSendBo.getWebMsg());
                    message.setUserId(user.getId());
                    messageSendUtil.sendMessage(message, accessToken);
                }

                //微信消息
                if (findObj.getVal2() != null
                        && MessageConstant.YWTX_WECHAT.equals(findObj.getVal2())
                        && StringUtils.isNotEmpty(user.getWxopenid())
                        && messageSendBo.getTemplateid() != null && messageSendBo.getDataList() != null) {

                    messageSendBo.getDataList().put("userId", user.getId());
                    messageSendBo.getDataList().put("openId", user.getWxopenid());
                    templateService.templateSend(messageSendBo.getTemplateid(), messageSendBo.getDataList());
                }

                //短信消息
                if (findObj.getVal3() != null
                        && MessageConstant.YWTX_MESSAGE.equals(findObj.getVal3())
                        && StringUtils.isNotEmpty(user.getPhone())
                        && messageSendBo.getPhoneMsg() != null) {
                    Map<String, String> maps = new HashMap<String, String>();
                    maps.put("var", messageSendBo.getPhoneMsg());
                    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                    list.add(maps);
                    messageSendUtil.sendPhoneMessage(user.getPhone(), MessageConstant.MESSAGE_UPYUN_TEMPLATE_529,
                            list, accessToken);
                }

                return ResponseEntity.ok(Utils.kv());
            } else {
                return ResponseEntity.ok(Utils.bodyStatus(9875, "消息发送权限未启用"));
            }
        } else {
            return ResponseEntity.ok(Utils.bodyStatus(9876, "用户不存在"));
        }
    }

    @Override
    public void sendMsg(User user, String sysMsg,String skipUrl, String templateid, Map<String, String> dataList, String dxmsg) {

        VipPrivilegeLevelBO obj = new VipPrivilegeLevelBO();
        obj.setLevelId(user.getVipLevel());
        obj.setPrivilegeId(MessageConstant.YWTX_CODE);
        VipPrivilegeLevelBO findObj = vipPrivilegeLevelRoMapper.selectLevelIdPrivilegeId(obj);

        //获取运营管理系统accessToken
        
        AppBO appBO=appService.selectByName("abc12366-admin");
        Date lastRest=appBO.getLastResetTokenTime();
        if(lastRest.before(new Date())){
        	appBO.setLastResetTokenTime(DateUtils.addHours(new Date(), 2));
        	appService.update(appBO);
        }       
        String accessToken = appBO.getAccessToken();
        LOGGER.info("获取运营管理系统accessToken:" + accessToken);

        //查看业务提醒是否启用
        if (findObj != null && findObj.getStatus()) {
            LOGGER.info("用户:" + user.getId() + ",提醒：" + sysMsg);

            //普通消息
            if (sysMsg != null) {
                Message message = new Message();
                message.setBusinessId(user.getId());
                message.setBusiType(MessageConstant.XTTX);
                message.setType(MessageConstant.SYS_MESSAGE);
                message.setContent(sysMsg);
                message.setUrl(skipUrl);
                message.setUserId(user.getId());
                messageSendUtil.sendMessage(message, accessToken);
            }

            //微信消息
            if (findObj.getVal2() != null
                    && MessageConstant.YWTX_WECHAT.equals(findObj.getVal2())
                    && StringUtils.isNotEmpty(user.getWxopenid())
                    && templateid != null && dataList != null) {
                dataList.put("userId", user.getId());
                dataList.put("openId", user.getWxopenid());
                templateService.templateSend(templateid, dataList);
            }

            //短信消息
            if (findObj.getVal3() != null
                    && MessageConstant.YWTX_MESSAGE.equals(findObj.getVal3())
                    && StringUtils.isNotEmpty(user.getPhone())
                    && dxmsg != null) {
                Map<String, String> maps = new HashMap<String, String>();
                maps.put("var", dxmsg);
                List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                list.add(maps);
                messageSendUtil.sendPhoneMessage(user.getPhone(), MessageConstant.MESSAGE_UPYUN_TEMPLATE_529, list,
                        accessToken);
            }
        }
    }

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity sendByQdbatch(MessageSendBo sendBo) {
		if(sendBo.getUserIds()==null || sendBo.getUserIds().size()==0){
			return ResponseEntity.ok(Utils.bodyStatus(9876, "批量发送用户为空!"));
		}else{
			for(String userid:sendBo.getUserIds()){
				User user = userService.selectUser(userid);
		        if (user != null) {

		            LOGGER.info("发送信息用户信息:" + JSONObject.toJSONString(user));

		            VipPrivilegeLevelBO obj = new VipPrivilegeLevelBO();
		            obj.setLevelId(user.getVipLevel());
		            obj.setPrivilegeId(MessageConstant.YWTX_CODE);
		            VipPrivilegeLevelBO findObj = vipPrivilegeLevelRoMapper.selectLevelIdPrivilegeId(obj);

		            //获取运营管理系统accessToken
		            AppBO appBO=appService.selectByName("abc12366-admin");
		            Date lastRest=appBO.getLastResetTokenTime();
		            if(lastRest.before(new Date())){
		            	appBO.setLastResetTokenTime(DateUtils.addHours(new Date(), 2));
		            	appService.update(appBO);
		            }       
		            String accessToken = appBO.getAccessToken();
		            LOGGER.info("获取运营管理系统accessToken:" + accessToken);

		            //查看业务提醒是否启用
		            if (findObj != null && findObj.getStatus()) {
		                LOGGER.info("用户:" + user.getId() + ",提醒：" + sendBo.getWebMsg());

		                //普通消息
		                if (sendBo.getWebMsg() != null) {
		                    Message message = new Message();
		                    message.setBusinessId(StringUtils.isEmpty(sendBo.getBusinessId()) ? user.getId() :
		                    	sendBo.getBusinessId());
		                    message.setBusiType(StringUtils.isEmpty(sendBo.getBusiType()) ? MessageConstant.XTTX :
		                    	sendBo.getBusiType());
		                    message.setType(MessageConstant.SYS_MESSAGE);
		                    message.setContent(sendBo.getWebMsg());
		                    message.setUserId(user.getId());
		                    messageSendUtil.sendMessage(message, accessToken);
		                }

		                //微信消息
		                if (findObj.getVal2() != null
		                        && MessageConstant.YWTX_WECHAT.equals(findObj.getVal2())
		                        && StringUtils.isNotEmpty(user.getWxopenid())
		                        && sendBo.getTemplateid() != null && sendBo.getDataList() != null) {

		                	sendBo.getDataList().put("userId", user.getId());
		                    sendBo.getDataList().put("openId", user.getWxopenid());
		                    templateService.templateSend(sendBo.getTemplateid(), sendBo.getDataList());
		                }

		                //短信消息
		                if (findObj.getVal3() != null
		                        && MessageConstant.YWTX_MESSAGE.equals(findObj.getVal3())
		                        && StringUtils.isNotEmpty(user.getPhone())
		                        && sendBo.getPhoneMsg() != null) {
		                    Map<String, String> maps = new HashMap<String, String>();
		                    maps.put("var", sendBo.getPhoneMsg());
		                    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		                    list.add(maps);
		                    messageSendUtil.sendPhoneMessage(user.getPhone(), MessageConstant.MESSAGE_UPYUN_TEMPLATE_529,
		                            list, accessToken);
		                }
		            } 
		        } 
			}
			
			return ResponseEntity.ok(Utils.kv());
		}
	}
	
	
	
	
	public void sendXtxx(MessageSendBo sendBo,User user){
		
		VipPrivilegeLevelBO obj = new VipPrivilegeLevelBO();
        obj.setLevelId(user.getVipLevel());
        obj.setPrivilegeId(MessageConstant.XTTX_CODE);
        VipPrivilegeLevelBO findObj = vipPrivilegeLevelRoMapper.selectLevelIdPrivilegeId(obj);
        
        //获取运营管理系统accessToken
        AppBO appBO=appService.selectByName("abc12366-admin");
        Date lastRest=appBO.getLastResetTokenTime();
        String accessToken = appBO.getAccessToken();
        if(lastRest.before(new Date())){
        	try {
				accessToken = appService.login(appBO);
			} catch (Exception e) {
				throw new ServiceException(9999,"消息发送失败:刷新accessToken异常!");
			}
        }       
        
        LOGGER.info("获取运营管理系统accessToken:" + accessToken);
        
        if (findObj != null && findObj.getStatus()) {
        	LOGGER.info("用户:" + user.getId() + ",提醒：" + sendBo.getWebMsg());

            //普通消息
            if (sendBo.getWebMsg() != null) {
                Message message = new Message();
                message.setBusinessId(StringUtils.isEmpty(sendBo.getBusinessId()) ? user.getId() :
                	sendBo.getBusinessId());
                message.setBusiType(StringUtils.isEmpty(sendBo.getBusiType()) ? MessageConstant.XTTX :
                	sendBo.getBusiType());
                message.setType(MessageConstant.SYS_MESSAGE);
                message.setContent(sendBo.getWebMsg());
                message.setUserId(user.getId());
                message.setUrl(sendBo.getSkipUrl());
                messageSendUtil.sendMessage(message, accessToken);
            }

            //微信消息
            if (findObj.getVal2() != null
                    && MessageConstant.YWTX_WECHAT.equals(findObj.getVal2())
                    && StringUtils.isNotEmpty(user.getWxopenid())
                    && sendBo.getTemplateid() != null && sendBo.getDataList() != null) {

            	sendBo.getDataList().put("userId", user.getId());
                sendBo.getDataList().put("openId", user.getWxopenid());
                templateService.templateSend(sendBo.getTemplateid(), sendBo.getDataList());
            }

            //短信消息
            if (findObj.getVal3() != null
                    && MessageConstant.YWTX_MESSAGE.equals(findObj.getVal3())
                    && StringUtils.isNotEmpty(user.getPhone())
                    && sendBo.getPhoneMsg() != null) {
                Map<String, String> maps = new HashMap<String, String>();
                maps.put("var", sendBo.getPhoneMsg());
                List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                list.add(maps);
                messageSendUtil.sendPhoneMessage(user.getPhone(), MessageConstant.MESSAGE_UPYUN_TEMPLATE_615,
                        list, accessToken);
            }
        }
		
	}

	@Override
	public ResponseEntity sendXtxx(MessageSendBo sendBo) {
		User user = userService.selectUser(sendBo.getUserId());
        if (user != null) {
        	sendXtxx(sendBo,user);
        	return ResponseEntity.ok(Utils.kv());
        }
        return ResponseEntity.ok(Utils.bodyStatus(9999, "用户信息不存在")); 
	}

	@Override
	public ResponseEntity sendXtxxbatch(MessageSendBo sendBo) {
		if(sendBo.getUserIds()==null || sendBo.getUserIds().size()==0){
			return ResponseEntity.ok(Utils.bodyStatus(9876, "批量发送用户为空!"));
		}else{
			for(String userid:sendBo.getUserIds()){
				User user = userService.selectUser(userid);
		        if (user != null) {
		        	sendXtxx(sendBo,user);
		        }
			}
		}
		return ResponseEntity.ok(Utils.kv());
	}
}
