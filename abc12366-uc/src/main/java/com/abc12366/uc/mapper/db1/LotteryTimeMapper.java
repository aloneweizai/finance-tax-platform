package com.abc12366.uc.mapper.db1;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-13
 */


import com.abc12366.uc.model.LotteryTime;

public interface LotteryTimeMapper {
    Integer insert(LotteryTime lotteryTime);
    Integer update(LotteryTime lotteryTime);
    Integer delete(String id);
}
