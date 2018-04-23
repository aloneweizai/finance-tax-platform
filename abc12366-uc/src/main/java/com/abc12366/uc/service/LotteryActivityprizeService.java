package com.abc12366.uc.service;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-21
 */


import com.abc12366.uc.model.bo.LotteryActivityprizeBO;

import java.util.List;
import java.util.Map;

public interface LotteryActivityprizeService {
    List<LotteryActivityprizeBO> selectList(Map map);
    LotteryActivityprizeBO selectOne(String id);
    LotteryActivityprizeBO insert(LotteryActivityprizeBO lotteryActivityprizeBO);
    LotteryActivityprizeBO update(LotteryActivityprizeBO lotteryActivityprizeBO, String id);
    boolean delete(String id);

}
