package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.bo.ExperienceRuleBO;

import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 11:27
 */
public interface ExperienceRuleRoMapper {
    List<ExperienceRuleBO> selectList(Map<String, Object> map);

    ExperienceRuleBO selectOne(String id);

    ExperienceRuleBO selectValidOne(String ruleId);

    ExperienceRuleBO selectValidOneByCode(String ruleCode);
}
