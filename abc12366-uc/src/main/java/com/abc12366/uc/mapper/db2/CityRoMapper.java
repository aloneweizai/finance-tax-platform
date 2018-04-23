package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CityMapper数据库操作接口类
 **/

public interface CityRoMapper {

    List<City> selectCityList(@Param("cityId") String cityId);

    List<City> selectCityByProId(@Param("provinceId") String provinceId);
}