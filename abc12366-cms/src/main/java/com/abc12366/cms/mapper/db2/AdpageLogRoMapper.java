package com.abc12366.cms.mapper.db2;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-08-25
 */


import com.abc12366.cms.model.bo.AdpageLogBO;

import java.util.List;
import java.util.Map;

public interface AdpageLogRoMapper {
    List<AdpageLogBO> selectList(Map map);
    AdpageLogBO selectOne(String id);
}
