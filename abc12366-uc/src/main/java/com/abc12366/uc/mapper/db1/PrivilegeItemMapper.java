package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.PrivilegeItem;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-22
 * Time: 11:36
 */
public interface PrivilegeItemMapper {
    int update(PrivilegeItem privilegeItem);

    int insert(PrivilegeItem privilegeItem);

    int delete(String privilegeId);
}
