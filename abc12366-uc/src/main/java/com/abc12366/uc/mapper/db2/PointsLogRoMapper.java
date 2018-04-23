package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.bo.PointsLogBO;
import com.abc12366.uc.model.bo.PointsLogUcBO;

import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-25
 * Time: 16:22
 */
public interface PointsLogRoMapper {
    List<PointsLogBO> selectList(Map map);

    List<PointsLogUcBO> selectListByUser(Map<String, Object> map);

	int selecttimes(Map map);
}
