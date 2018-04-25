package com.abc12366.uc.service;

import com.abc12366.uc.model.Dict;
import com.abc12366.uc.model.UserBank;
import com.abc12366.uc.model.bo.UserBankBO;

import java.util.List;
import java.util.Map;

/**
 * 数据用户银行卡服务
 *
 * @create 2017-04-24 3:09 PM
 * @since 1.0.0
 */
public interface UserBankService {


    /**
     * 新增用户银行卡
     * @return UserBank
     * @see UserBank
     * @param userBank
     */
    UserBankBO insert(UserBankBO userBank);

    /**
     * 更新用户银行卡
     * @param userBank
     */
    UserBankBO update(UserBankBO userBank);

    /**
     * 根据ID删除用户银行卡
     *
     * @param id 用户银行卡主键
     * @return int 影响记录行数
     */
    int delete(String id);


    List<UserBank> selectUserBankList(Map<String, Object> map);

    UserBank selectById(String id);
}
