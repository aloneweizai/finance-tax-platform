package com.abc12366.uc.service;

import com.abc12366.uc.model.UserPointsApply;
import com.abc12366.uc.model.UserPointsApply;
import com.abc12366.uc.model.bo.UserPointsApplyBO;

import java.util.List;
import java.util.Map;

/**
 * 数据用户积分返还服务
 *
 * @create 2017-04-24 3:09 PM
 * @since 1.0.0
 */
public interface UserPointsApplyService {


    /**
     * 新增用户积分返还
     */
    UserPointsApplyBO insert(UserPointsApplyBO userPointsApply);

    /**
     * 更新用户积分返还
     */
    UserPointsApplyBO update(UserPointsApplyBO userPointsApply);

    /**
     * 根据ID删除用户积分返还
     */
    int delete(String id);


    List<UserPointsApply> selectUserPointsApplyList(Map<String, Object> map);

    UserPointsApply selectById(String id);

}
