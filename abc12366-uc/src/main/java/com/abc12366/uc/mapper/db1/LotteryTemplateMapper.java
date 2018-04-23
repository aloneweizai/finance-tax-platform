package com.abc12366.uc.mapper.db1;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-19
 */


import com.abc12366.uc.model.LotteryTemplate;

public interface LotteryTemplateMapper {
    Integer insert(LotteryTemplate lotteryTemplate);
    Integer update(LotteryTemplate lotteryTemplate);
    Integer delete(String id);
}
