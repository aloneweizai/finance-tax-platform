package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.bo.PointCodex;
import com.abc12366.uc.model.bo.PointComputeLog;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-30
 * Time: 19:37
 */
public interface PointMapper {
    int insertComputeLog(PointComputeLog pointComputeLog);

    int deleteByRuleCode(String upointCodexId);

    int insert(PointCodex codex);

    int deleteCodex(String id);
}
