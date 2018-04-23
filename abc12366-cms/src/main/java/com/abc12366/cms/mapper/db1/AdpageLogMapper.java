package com.abc12366.cms.mapper.db1;

/**
 * Admin: lingsuzhi <554600654@qq.com.com>
 * Date: 2017-08-25
 */


import com.abc12366.cms.model.AdpageLog;

public interface AdpageLogMapper {
    Integer insert(AdpageLog adpageLog);
    Integer update(AdpageLog adpageLog);
    Integer delete(String id);
}
