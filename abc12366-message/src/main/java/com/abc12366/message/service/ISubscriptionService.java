package com.abc12366.message.service;

import java.util.List;
import java.util.Map;

import com.abc12366.message.model.Subscriptions;
import com.abc12366.message.model.UserSubscription;
import com.abc12366.message.model.UserSubscriptionInfo;

public interface ISubscriptionService {

	/**
	 * 订阅设置列表
	 * @param param
	 * @param page
	 * @param size
	 * @return
	 */
	List<Subscriptions> selectList(Map<String, Object> param, int page, int size);

	/**
	 * 查询单个订阅设置
	 * @param id
	 * @return
	 */
	Subscriptions selectOneSetting(String id);

	/**
	 * 消息订阅设置删除
	 * @param id
	 * @return
	 */
	int delOneSetting(String id);

	/**
	 * 更新消息订阅设置
	 * @param subscriptions
	 * @return
	 */
	Subscriptions updateOneSetting(Subscriptions subscriptions);

	/**
	 * 新增消息订阅设置
	 * @param subscriptions
	 * @return
	 */
	Subscriptions addOneSetting(Subscriptions subscriptions);

	/**
	 * 初始化订阅列表
	 * @param userId
	 * @return
	 */
	int initial(String userId);

	/**
	 * 个人消息订阅设置列表
	 * @param userId
	 * @return
	 */
	List<UserSubscriptionInfo> selectUserSubscriptionList(Map<String, Object> param);

	/**
	 * 保存个人设置订阅消息
	 * @param userId
	 * @param dataList
	 * @return
	 */
	int userSetSave(String userId, List<UserSubscription> dataList);
	
	/**
	 * 发送消息时查询单条订阅消息设置
	 * @param userId
	 * @param type
	 * @param busiType
	 * @return
	 */
	UserSubscriptionInfo getUserOne(String userId,String type,String busiType);

}
