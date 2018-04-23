package com.abc12366.uc.mapper.db2;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-21
 */


import com.abc12366.uc.model.bo.LotteryActivityBO;

import java.util.List;
import java.util.Map;

public interface LotteryActivityRoMapper {
    List<LotteryActivityBO> selectList(Map map);
    LotteryActivityBO selectOne(String id);
}
