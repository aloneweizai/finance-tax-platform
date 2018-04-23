package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.SysTask;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-24
 * Time: 9:53
 */
public interface SysTaskMapper {
    int insert(SysTask sysTask);

    int update(SysTask sysTask);

    int delete(String id);
}
