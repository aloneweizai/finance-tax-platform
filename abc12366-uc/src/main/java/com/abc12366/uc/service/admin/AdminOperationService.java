package com.abc12366.uc.service.admin;

import com.abc12366.uc.model.bo.AdminModifyUphoneLogList;
import com.abc12366.uc.model.bo.AdminModifyUserPhoneLogBO;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-15
 * Time: 14:55
 */
public interface AdminOperationService {

    /**
     * 管理员修改用户手机日志
     * @param logBO AdminModifyUserPhoneLogBO
     * @return AdminModifyUserPhoneLogBO
     */
    AdminModifyUserPhoneLogBO insert(AdminModifyUserPhoneLogBO logBO);

    /**
     * 查询管理员修改用户手机日志
     * @param userId 用户ID
     * @return AdminModifyUphoneLogList
     */
    List<AdminModifyUphoneLogList> selectList(String userId);
}
