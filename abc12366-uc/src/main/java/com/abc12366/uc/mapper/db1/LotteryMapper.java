package com.abc12366.uc.mapper.db1;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-20
 */


import com.abc12366.uc.model.Lottery;

public interface LotteryMapper {
    Integer insert(Lottery lottery);
    Integer update(Lottery lottery);
    Integer delete(String id);
}
