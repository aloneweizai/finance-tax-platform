package com.abc12366.uc.service;

import com.abc12366.uc.model.bo.ExperienceRuleBO;
import com.abc12366.uc.model.bo.ExperienceRuleInsertBO;
import com.abc12366.uc.model.bo.ExperienceRuleUpdateBO;

import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 11:24
 */
public interface ExperienceRuleService {
    List<ExperienceRuleBO> selectList(Map<String, Object> map);

    ExperienceRuleBO selectOne(String id);

    ExperienceRuleBO insert(ExperienceRuleInsertBO experienceRuleInsertBO);

    ExperienceRuleBO update(ExperienceRuleUpdateBO experienceRuleUpdateBO, String id);

    int delete(String id);

    void enableOrDisable(String id, String status);

    ExperienceRuleBO selectValidOne(String ruleId);

    //修改停用经验值规则之前做校验：是否有关联的任务在使用此条规则，若有，则不允许修改此条规则
    void isValidSysTaskRelatedTheRule(String ruleId);

    //根据经验值规则编码查询一条经验值规则
    ExperienceRuleBO selectValidOneByCode(String ruleCode);
}
