package com.abc12366.uc.service;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-11-11
 */


import com.abc12366.uc.model.bo.LotteryActivityipBO;

import java.util.List;
import java.util.Map;

public interface LotteryActivityipService {
    List<LotteryActivityipBO> selectList(Map map);
    LotteryActivityipBO selectOne(String id);
    LotteryActivityipBO insert(LotteryActivityipBO lotteryActivityipBO);
    LotteryActivityipBO update(LotteryActivityipBO lotteryActivityipBO, String id);
    boolean delete(String id);

}
