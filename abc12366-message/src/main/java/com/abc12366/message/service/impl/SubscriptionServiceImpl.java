package com.abc12366.message.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.message.mapper.db1.SubscriptionMapper;
import com.abc12366.message.mapper.db2.SubscriptionRoMapper;
import com.abc12366.message.model.Subscriptions;
import com.abc12366.message.model.UserSubscription;
import com.abc12366.message.model.UserSubscriptionInfo;
import com.abc12366.message.service.ISubscriptionService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;

@Service
public class SubscriptionServiceImpl implements ISubscriptionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ISubscriptionService.class);

	@Autowired
	private SubscriptionMapper subscriptionMapper;

	@Autowired
	private SubscriptionRoMapper subscriptionRoMapper;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public List<Subscriptions> selectList(Map<String, Object> param, int page, int size) {
		PageHelper.startPage(page, size, true).pageSizeZero(true).reasonable(true);
		return subscriptionRoMapper.selectList(param);
	}

	@Override
	public Subscriptions selectOneSetting(String id) {
		return subscriptionRoMapper.selectOneSetting(id);
	}

	@Transactional("db1TxManager")
	public int delOneSetting(String id) {
		// 先删除所有订阅了该消息的人
		subscriptionMapper.delUserSetting(id);
		return subscriptionMapper.delOneSetting(id);
	}

	@Transactional("db1TxManager")
	public Subscriptions updateOneSetting(Subscriptions subscriptions) {
		subscriptions.setLastUpdate(new Date());
		subscriptionMapper.updateOneSetting(subscriptions);
		return subscriptions;
	}

	@Transactional("db1TxManager")
	public Subscriptions addOneSetting(Subscriptions subscriptions) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("type", subscriptions.getType());
		param.put("busiType", subscriptions.getBusiType());
		List<Subscriptions> list = subscriptionRoMapper.selectList(param);
		if (list != null && list.size() > 0) {
			throw new ServiceException(9999, "已经存在此消息类型和业务类型相同的消息订阅!");
		}
		Date date = new Date();
		subscriptions.setCreateTime(date);
		subscriptions.setLastUpdate(date);
		subscriptions.setId(Utils.uuid());
		subscriptionMapper.addOneSetting(subscriptions);
		return subscriptions;
	}

	@Transactional("db1TxManager")
	public int initial(String userId) {
		// 清空订阅关系表
		int n = subscriptionMapper.delAUserSetting(userId);
		LOGGER.info("清空订阅关系条数:" + n);

		Date date = new Date();
		List<Subscriptions> subscriptions = subscriptionRoMapper.selectList(new HashMap<String, Object>());
		List<UserSubscription> userSubscriptions = new ArrayList<UserSubscription>();
		if (subscriptions != null && subscriptions.size() > 0) {
			for (Subscriptions subscription : subscriptions) {
				UserSubscription userSubscription = new UserSubscription();
				userSubscription.setId(Utils.uuid());
				userSubscription.setUserId(userId);
				userSubscription.setSettingId(subscription.getId());
				userSubscription.setCreateTime(date);
				userSubscription.setLastUpdate(date);
				if (subscription.getHasMessage()) {// 判断是否有手机信息
					userSubscription.setMessage(true);
				} else {
					userSubscription.setMessage(false);
				}
				if (subscription.getHasWeb()) {// 判断是否有站内
					userSubscription.setWeb(true);
				} else {
					userSubscription.setWeb(false);
				}
				if (subscription.getHasWechat()) {// 判断是否有微信
					userSubscription.setWechat(true);
				} else {
					userSubscription.setWechat(false);
				}
				userSubscriptions.add(userSubscription);

				// 删除reids缓存
				redisTemplate.delete(
						"subscription_" + userId + "_" + subscription.getType() + "_" + subscription.getBusiType());
			}

			n = subscriptionMapper.insertBatch(userSubscriptions);
			LOGGER.info("批量插入订阅关系条数:" + n);
		}

		return n;
	}

	@Override
	public List<UserSubscriptionInfo> selectUserSubscriptionList(Map<String, Object> param) {
		return subscriptionRoMapper.selectUserSubscriptionList(param);
	}

	@Override
	public int userSetSave(String userId, List<UserSubscription> dataList) {
		// 清空订阅关系表
		int n = subscriptionMapper.delAUserSetting(userId);
		LOGGER.info("清空订阅关系条数:" + n);
		Date date = new Date();
		if (dataList != null && dataList.size() > 0) {
			for (UserSubscription subscription : dataList) {
				subscription.setId(Utils.uuid());
				subscription.setCreateTime(date);
				subscription.setLastUpdate(date);
			}
			n = subscriptionMapper.insertBatch(dataList);
		}

		// 删除reids缓存
		Set<String> keys = redisTemplate.keys("subscription_" + userId + "_*");
		Iterator<String> it = keys.iterator();  
		while (it.hasNext()) {  
		   String key = it.next();  
		   redisTemplate.delete(key);
		}
		return n;
	}

	@Override
	public UserSubscriptionInfo getUserOne(String userId, String type, String busiType) {
		String redisKey = "subscription_" + userId + "_" + type + "_" + busiType;
		if (redisTemplate.hasKey(redisKey)) {
			return JSONObject.parseObject(redisTemplate.opsForValue().get(redisKey), UserSubscriptionInfo.class);
		} else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", userId);
			param.put("type", type);
			param.put("busiType", busiType);
			List<UserSubscriptionInfo> list = subscriptionRoMapper.selectUserSubscriptionList(param);
			if (list != null && list.size() > 0) {
				UserSubscriptionInfo subscriptionInfo = list.get(0);
				redisTemplate.opsForValue().set(redisKey, JSONObject.toJSONString(subscriptionInfo));
				return subscriptionInfo;
			}
		}
		return null;
	}
}
