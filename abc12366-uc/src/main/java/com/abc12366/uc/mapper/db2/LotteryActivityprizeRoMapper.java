package com.abc12366.uc.mapper.db2;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-21
 */


import com.abc12366.uc.model.bo.LotteryActivityprizeBO;

import java.util.List;
import java.util.Map;

public interface LotteryActivityprizeRoMapper {
    List<LotteryActivityprizeBO> selectList(Map map);
    LotteryActivityprizeBO selectOne(String id);
}
