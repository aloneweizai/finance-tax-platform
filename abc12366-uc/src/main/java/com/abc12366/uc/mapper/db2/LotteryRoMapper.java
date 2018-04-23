package com.abc12366.uc.mapper.db2;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-20
 */


import com.abc12366.uc.model.bo.LotteryBO;

import java.util.List;
import java.util.Map;

public interface LotteryRoMapper {
    List<LotteryBO> selectList(Map map);
    List<LotteryBO> findLotteryByActivity(Map map);
    LotteryBO selectOne(String id);
}
