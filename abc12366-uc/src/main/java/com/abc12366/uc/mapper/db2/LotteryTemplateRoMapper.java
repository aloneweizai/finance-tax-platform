package com.abc12366.uc.mapper.db2;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-09-19
 */


import com.abc12366.uc.model.bo.LotteryTemplateBO;

import java.util.List;
import java.util.Map;

public interface LotteryTemplateRoMapper {
    List<LotteryTemplateBO> selectList(Map map);
    LotteryTemplateBO selectOne(String id);
}
