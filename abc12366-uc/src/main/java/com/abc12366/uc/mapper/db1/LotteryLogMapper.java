package com.abc12366.uc.mapper.db1;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-18
 */


import com.abc12366.uc.model.LotteryLog;

public interface LotteryLogMapper {
    Integer insert(LotteryLog lotteryLog);
    Integer update(LotteryLog lotteryLog);
    Integer delete(String id);
}
