package com.abc12366.uc.mapper.db2;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-11-11
 */


import com.abc12366.uc.model.bo.LotteryActivityipBO;

import java.util.List;
import java.util.Map;

public interface LotteryActivityipRoMapper {
    List<LotteryActivityipBO> selectList(Map map);
    LotteryActivityipBO selectOne(String id);
}
