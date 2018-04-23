package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AreaMapper数据库操作接口类
 **/

public interface AreaRoMapper {

    List<Area> selectAreaByCityId(@Param("cityId") String cityId);

    List<Area> selectAreaList(@Param("areaId") String areaId);
}