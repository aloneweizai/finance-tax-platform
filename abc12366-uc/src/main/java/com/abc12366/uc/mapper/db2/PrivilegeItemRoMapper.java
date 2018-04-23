package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.PrivilegeItem;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-22
 * Time: 11:37
 */
public interface PrivilegeItemRoMapper {
    PrivilegeItem selectOne(String levelId);

    PrivilegeItem selecOneByUser(String userId);

    PrivilegeItem selectOneByLevelCode(String userOriginalLevel);
}
