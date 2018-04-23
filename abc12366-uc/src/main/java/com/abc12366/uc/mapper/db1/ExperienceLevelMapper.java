package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.ExperienceLevel;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 16:00
 */
public interface ExperienceLevelMapper {
    int insert(ExperienceLevel experienceLevel);

    int update(ExperienceLevel experienceLevel);

    int delete(String id);

    int enableOrDisable(ExperienceLevel experienceLevel);
}
