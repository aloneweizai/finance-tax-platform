package com.abc12366.uc.service;

import com.abc12366.uc.model.bo.UserExtendBO;
import com.abc12366.uc.model.bo.UserExtendListBO;
import com.abc12366.uc.model.bo.UserExtendUpdateBO;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-04
 * Time: 11:07
 */
public interface RealNameValidationService {

    /**
     * 查询实名认证列表
     *
     * @param map  查询条件
     * @param page 页码
     * @param size 每页数据量
     * @return 实名认证列表
     */
    List<UserExtendListBO> selectList(Map<String, Object> map, int page, int size);

    /**
     * 手动审核实名认证
     *
     * @param userId             用户ID
     * @param validStatus        认证状态
     * @param userExtendUpdateBO 用户扩展信息
     * @return 用户扩展信息
     * @throws ParseException 日期转换异常
     */
    UserExtendBO validate(String userId, String validStatus, UserExtendUpdateBO userExtendUpdateBO) throws
            ParseException;

    /**
     * 查询认证状态为【待认证】数量
     *
     * @return Integer 数量
     */
    Integer selectTodoListCount();
}
