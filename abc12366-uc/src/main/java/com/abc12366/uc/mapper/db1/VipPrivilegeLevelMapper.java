package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.VipPrivilegeLevel;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 10:29
 */
public interface VipPrivilegeLevelMapper {
    int insert(VipPrivilegeLevel vipPrivilege);

    int update(VipPrivilegeLevel vipPrivilege);

    int delete(String id);
    int deleteByLevel(String levelId);
    int deleteByPrivilege(String PrivilegeId);
}
