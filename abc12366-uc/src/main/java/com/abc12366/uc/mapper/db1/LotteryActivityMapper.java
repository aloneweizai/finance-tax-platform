package com.abc12366.uc.mapper.db1;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-21
 */


import com.abc12366.uc.model.LotteryActivity;

public interface LotteryActivityMapper {
    Integer insert(LotteryActivity lotteryActivity);
    Integer update(LotteryActivity lotteryActivity);
    Integer delete(String id);
}
