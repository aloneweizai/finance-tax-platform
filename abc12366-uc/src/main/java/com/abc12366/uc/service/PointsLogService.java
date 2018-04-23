package com.abc12366.uc.service;

import com.abc12366.uc.model.bo.PointsLogBO;
import com.abc12366.uc.model.bo.PointsLogUcBO;

import java.util.List;
import java.util.Map;

/**
 * @author liuguiyao<435720953@qq.com.com>
 * @create 2017-05-15 10:18 PM
 * @since 2.0.0
 */
public interface PointsLogService {
    List<PointsLogBO> selectList(Map map);

    PointsLogBO insert(PointsLogBO pointsLogBO);

    List<PointsLogUcBO> selectListByUser(Map<String, Object> map);

    PointsLogBO insertNoVip(PointsLogBO pointsLogBO);

    PointsLogBO insertByConsume(PointsLogBO pointsLogBO,String vipLevel);
    /**
     * 查询本月装让次数
     * @param map
     * @return
     */
    int selecttimes (Map map);
}
