package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.Province;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ProvinceMapper数据库操作接口类
 **/

public interface ProvinceRoMapper {

    List<Province> selectProvinceList(@Param("provinceId") String provinceId);
}