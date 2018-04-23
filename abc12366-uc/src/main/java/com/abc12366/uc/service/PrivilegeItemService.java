package com.abc12366.uc.service;

import com.abc12366.uc.model.PrivilegeItem;
import com.abc12366.uc.model.bo.PrivilegeItemBO;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-22
 * Time: 10:30
 */
public interface PrivilegeItemService {
    /**
     * 获取用户会员的权益
     * @param levelId
     * @return
     */
    PrivilegeItem selectOne(String levelId);

    /**
     * 修改用户会员权益
     * @param privilegeItemBO
     * @return
     */
    PrivilegeItem update(PrivilegeItemBO privilegeItemBO);

    /**
     * 新增用户会员权益
     * @param privilegeItemBO
     * @return
     */
    PrivilegeItem insert(PrivilegeItemBO privilegeItemBO);

    /**
     * 删除用户会员权益
     * @param levelId
     * @return
     */
    int delete(String levelId);

    /**
     * 根据用户ID获取用户会员权益
     * @param userId
     * @return
     */
    PrivilegeItem selecOneByUser(String userId);
}
