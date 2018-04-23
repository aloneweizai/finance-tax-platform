package com.abc12366.uc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.model.bo.AppBO;
import com.abc12366.gateway.service.AppService;
import com.abc12366.gateway.util.MessageConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.model.UserSubscriptionInfo;
import com.abc12366.uc.model.bo.VipPrivilegeLevelBO;
import com.abc12366.uc.mapper.db2.VipPrivilegeLevelRoMapper;
import com.abc12366.uc.model.Message;
import com.abc12366.uc.model.MessageSendBo;
import com.abc12366.uc.model.User;
import com.abc12366.uc.service.IMsgSendV2service;
import com.abc12366.uc.service.IWxTemplateService;
import com.abc12366.uc.service.MessageSendUtil;
import com.abc12366.uc.service.UserService;
import com.alibaba.fastjson.JSONObject;

/**
 * 新版本消息发送接口实现类 V2
 * 
 * @author Administrator
 *
 */
@Service("msgSendV2Service")
public class MsgSendV2serviceImpl implements IMsgSendV2service {

	private static final Logger LOGGER = LoggerFactory.getLogger(IMsgSendV2service.class);

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

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@SuppressWarnings("rawtypes")
	public ResponseEntity sendMsgV2(MessageSendBo messageSendBo) {
		try {
			if (StringUtils.isEmpty(messageSendBo.getType()) || StringUtils.isEmpty(messageSendBo.getBusiType())) {
				return ResponseEntity.ok(Utils.bodyStatus(9875, "消息类型或业务类型为空!"));
			}
			if (messageSendBo.getUserIds() == null || messageSendBo.getUserIds().size() == 0) {
				return ResponseEntity.ok(Utils.bodyStatus(9875, "消息接收用户为空!"));
			}
			// 获取运营管理系统accessToken
			AppBO appBO = appService.selectByName("abc12366-admin");
			Date lastRest = appBO.getLastResetTokenTime();
			String accessToken = appBO.getAccessToken();
			if (lastRest.before(new Date())) {
				try {
					accessToken = appService.login(appBO);
				} catch (Exception e) {
					//throw new ServiceException(9999, "消息发送失败:刷新accessToken异常!");
					return ResponseEntity.ok(Utils.bodyStatus(9875, "消息发送失败:刷新accessToken异常!"));
				}
			}

			LOGGER.info("开始发送消息，接收用户：{}", JSONObject.toJSONString(messageSendBo.getUserIds()));

			for (String userId : messageSendBo.getUserIds()) {
				Boolean doMsg = false, doWechat = false, doWeb = false;
				User user = userService.selectUser(userId);
				if (user != null) {
					LOGGER.info("接收消息用户信息：{}", JSONObject.toJSONString(user));
					// 查询个人消息订阅信息
					UserSubscriptionInfo info = getUserOne(userId, messageSendBo.getType(), messageSendBo.getBusiType());
					LOGGER.info("用户消息订阅情况信息：{}", JSONObject.toJSONString(info));
					if (info == null) {
						doMsg = true;
						doWechat = true;
						doWeb = true;
					} else {
						doMsg = info.getMessage();
						doWechat = info.getWechat();
						doWeb = info.getWeb();
					}

					// 查询Vip信息
					VipPrivilegeLevelBO obj = new VipPrivilegeLevelBO();
					obj.setLevelId(user.getVipLevel());
					obj.setPrivilegeId(MessageConstant.YWTX_CODE);
					VipPrivilegeLevelBO findObj = vipPrivilegeLevelRoMapper.selectLevelIdPrivilegeId(obj);

					if (findObj != null && findObj.getStatus()) {
						// 普通消息
						if (messageSendBo.getWebMsg() != null && doWeb) {
							LOGGER.info("发送站内消息........");
							Message message = new Message();
							message.setBusinessId(StringUtils.isEmpty(messageSendBo.getBusinessId()) ? user.getId()
									: messageSendBo.getBusinessId());
							message.setBusiType(messageSendBo.getBusiType());
							message.setType(messageSendBo.getType());
							message.setContent(messageSendBo.getWebMsg());
							message.setUserId(user.getId());
							message.setUrl(messageSendBo.getSkipUrl());
							messageSendUtil.sendMessage(message, accessToken);
						}

						// 微信消息
						if (doWechat // 是否订阅
								&& (messageSendBo.getWxNoChargeVip()
										|| MessageConstant.YWTX_WECHAT.equals(findObj.getVal2())) // 是否忽略VIP等级或者vip是否有微信消息权限
								&& StringUtils.isNotEmpty(user.getWxopenid()) // 是否存在openid
								&& messageSendBo.getTemplateid() != null // 是否填写模板ID
								&& messageSendBo.getDataList() != null) { // 是否存在模板数据
							LOGGER.info("发送微信消息........");
							messageSendBo.getDataList().put("userId", user.getId());
							messageSendBo.getDataList().put("openId", user.getWxopenid());
							templateService.templateSend(messageSendBo.getTemplateid(), messageSendBo.getDataList());
						}

						// 短信消息
						if (doMsg
								&& (messageSendBo.getMoblieNoChargeVip()
										|| MessageConstant.YWTX_MESSAGE.equals(findObj.getVal3()))
								&& StringUtils.isNotEmpty(user.getPhone()) && messageSendBo.getPhoneMsg() != null) {
							LOGGER.info("发送短信消息........");
							Map<String, String> maps = new HashMap<String, String>();
							maps.put("var", messageSendBo.getPhoneMsg());
							List<Map<String, String>> list = new ArrayList<Map<String, String>>();
							list.add(maps);
							messageSendUtil.sendPhoneMessage(user.getPhone(), MessageConstant.MESSAGE_UPYUN_TEMPLATE_529,
									list, accessToken);
						}
					}
					LOGGER.info("用户消息发送完毕,ID:{}", userId);
				} else {
					LOGGER.info("消息发送失败，用户信息异常：{}", userId);
				}
			}
		} catch (Exception e) {
			LOGGER.info("消息发送失败异常..............................");
			return ResponseEntity.ok(Utils.bodyStatus(9875, "消息发送失败异常!"));
		}
		return ResponseEntity.ok(Utils.kv());
	}

	/**
	 * 获取个人的消息订阅情况
	 * 
	 * @param userId
	 * @param type
	 * @param busiType
	 * @return
	 */
	public UserSubscriptionInfo getUserOne(String userId, String type, String busiType) {
		String redisKey = "subscription_" + userId + "_" + type + "_" + busiType;
		if (redisTemplate.hasKey(redisKey)) {
			return JSONObject.parseObject(redisTemplate.opsForValue().get(redisKey), UserSubscriptionInfo.class);
		} else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", userId);
			param.put("type", type);
			param.put("busiType", busiType);
			List<UserSubscriptionInfo> list = userService.selectUserSubscriptionList(param);
			if (list != null && list.size() > 0) {
				UserSubscriptionInfo subscriptionInfo = list.get(0);
				redisTemplate.opsForValue().set(redisKey, JSONObject.toJSONString(subscriptionInfo));
				return subscriptionInfo;
			}
		}
		return null;
	}
}
