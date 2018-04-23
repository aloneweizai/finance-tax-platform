package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.bo.ExpLogUcBO;
import com.abc12366.uc.model.bo.ExperienceLogQueryBO;

import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 15:16
 */
public interface ExperienceLogRoMapper {
    List<ExperienceLogQueryBO> selectList(Map map);

    ExperienceLogQueryBO selectOne(String id);

    List<ExpLogUcBO> selectListByUser(Map<String, Object> map);

    float selectCount(Map<String, Object> map);
}
