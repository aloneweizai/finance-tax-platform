package com.abc12366.uc.service;

import com.abc12366.uc.model.bo.ExpLogUcBO;
import com.abc12366.uc.model.bo.ExperienceLogBO;
import com.abc12366.uc.model.bo.ExperienceLogQueryBO;

import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 14:40
 */
public interface ExperienceLogService {
    ExperienceLogQueryBO insert(ExperienceLogBO experienceLogBO);

    List<ExperienceLogQueryBO> selectList(Map map);

    List<ExpLogUcBO> selectListByUser(Map<String, Object> map);

    float selectCount(Map<String, Object> map);
}
