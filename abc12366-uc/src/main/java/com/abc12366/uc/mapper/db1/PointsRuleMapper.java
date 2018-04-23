package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.PointsRule;

/**
 * @author liuguiyao<435720953@qq.com.com>
 * @create 2017-05-15 10:18 PM
 * @since 2.0.0
 */
public interface PointsRuleMapper {
    int insert(PointsRule pointsRule);

    int update(PointsRule pointsRule);

    int delete(String id);

    int enableOrDisable(PointsRule pointsRule);
}
