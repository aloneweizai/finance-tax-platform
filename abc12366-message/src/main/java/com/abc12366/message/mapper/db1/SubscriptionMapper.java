package com.abc12366.message.mapper.db1;

import java.util.List;

import com.abc12366.message.model.Subscriptions;
import com.abc12366.message.model.UserSubscription;

public interface SubscriptionMapper {

	int delOneSetting(String id);

	void updateOneSetting(Subscriptions subscriptions);

	void addOneSetting(Subscriptions subscriptions);

	void delUserSetting(String id);

	int delAUserSetting(String userId);
	
	int insertBatch(List<UserSubscription> userSubscriptions);

}
