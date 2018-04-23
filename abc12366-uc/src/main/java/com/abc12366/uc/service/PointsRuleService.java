package com.abc12366.uc.service;

import com.abc12366.uc.model.bo.PointsRuleBO;
import com.abc12366.uc.model.bo.PointsRuleInsertBO;
import com.abc12366.uc.model.bo.PointsRuleUpdateBO;

import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 9:13
 */
public interface PointsRuleService {
    List<PointsRuleBO> selectList(Map map);

    PointsRuleBO selectOne(String id);

    PointsRuleBO insert(PointsRuleInsertBO pointsRuleInsertBO);

    PointsRuleBO update(PointsRuleUpdateBO pointsRuleUpdateBO, String id);

    int delete(String id);

    void enableOrDisable(String id, String status);

    PointsRuleBO selectValidOne(String ruleId);

    //根据编码查询一条积分规则
    PointsRuleBO selectValidOneByCode(String ruleCode);
}
