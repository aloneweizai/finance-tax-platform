package com.abc12366.uc.service;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-18
 */


import com.abc12366.uc.model.bo.LotteryLogBO;

import java.util.List;
import java.util.Map;

public interface LotteryLogService {
    List<LotteryLogBO> selectList(Map map);
    LotteryLogBO selectOne(String id);
    Integer selectUserDay(String userId);
    Integer selectUserDayLuck(String userId);
    LotteryLogBO insert(LotteryLogBO lotteryLogBO);
    LotteryLogBO update(LotteryLogBO lotteryLogBO, String id);
    boolean delete(String id);

}
