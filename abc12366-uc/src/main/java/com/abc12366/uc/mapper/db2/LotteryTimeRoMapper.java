package com.abc12366.uc.mapper.db2;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-13
 */


import com.abc12366.uc.model.bo.LotteryTimeBO;

import java.util.List;
import java.util.Map;

public interface LotteryTimeRoMapper {
    List<LotteryTimeBO> selectList(Map map);
    LotteryTimeBO selectOne(String id);
}
