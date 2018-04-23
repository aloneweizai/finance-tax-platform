package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.ExperienceRule;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 11:40
 */
public interface ExperienceRuleMapper {
    int insert(ExperienceRule experienceRule);

    int update(ExperienceRule experienceRule);

    int delete(String id);

    int enableOrDisable(ExperienceRule experienceRule);
}
