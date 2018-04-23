package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.bo.*;

import java.util.List;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-25
 * Time: 16:38
 */
public interface ExperienceRoMapper {
    MyExperienceBO getMyExperience(String userId);

    List<ExpCodex> codexList();

    List<ExpCodex> selectOne(ExpComputeBO expComputeBO);

    List<ExpComputeLog> selectExpComputeLog( ExpComputeLogParam param);

    List<ExpComputeLog> selectCalculateLog(ExpComputeLogParam param);
}
