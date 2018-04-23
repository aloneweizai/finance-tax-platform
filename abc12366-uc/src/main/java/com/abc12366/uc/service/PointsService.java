package com.abc12366.uc.service;


import com.abc12366.uc.model.bo.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-20
 * Time: 21:43
 */
public interface PointsService {
    /**
     * 查询用户积分概况
     * @param userId 用户ID
     * @return PointsBO {@linkplain com.abc12366.uc.model.bo.PointsBO}
     */
    PointsBO selectOne(String userId);

    void compute(PointComputeBO pointComputeBO);

    List<PointCodex> codex(String upointCodexId, List<PointCodex> codexList);

    void deleteCodex(String id);

    List<PointCodex> codexList();

    PointCodex selectCodexByRuleCode(String ruleCode);

    /**
     * 根据积分规则根据用户的操作改变其积分值，并记日志
     * @param pointCalculateBO {@linkplain com.abc12366.uc.model.bo.PointCalculateBO}
     * @return
     */
    int calculate(PointCalculateBO pointCalculateBO);

    /**
     * 批量奖励用户积分
     * @param pointBatchAwardBO {@linkplain com.abc12366.uc.model.bo.PointBatchAwardBO}
     */
    void batchAward(PointBatchAwardBO pointBatchAwardBO);
    
    /**
     * 积分转让操作
     * @param sendUser  发送者
     * @param reciveUser  接受者
     * @param bo 积分规则
     * @return
     */
    ResponseEntity integralMultiplication(UserBO sendUser,UserBO reciveUser,PointsRuleBO bo);
}
