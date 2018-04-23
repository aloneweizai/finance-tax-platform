package com.abc12366.cms.service;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-08-25
 */


import com.abc12366.cms.model.bo.AdpageLogBO;

import java.util.List;
import java.util.Map;

public interface AdpageLogService {
    List<AdpageLogBO> selectList(Map map);
    AdpageLogBO selectOne(String id);
    AdpageLogBO insert(AdpageLogBO adpageLogBO);
    AdpageLogBO update(AdpageLogBO adpageLogBO, String id);
    boolean delete(String id);

}
