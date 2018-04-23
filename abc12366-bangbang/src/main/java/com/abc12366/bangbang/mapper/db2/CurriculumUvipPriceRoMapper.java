package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.curriculum.CurriculumUvipPrice;
import com.abc12366.bangbang.model.curriculum.bo.CurriculumUvipPriceBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * CurriculumUvipPriceMapper数据库操作接口类
 * 
 **/

public interface CurriculumUvipPriceRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CurriculumUvipPrice selectByPrimaryKey(@Param("id") String id);


    /**
     * 查询(根据查询条件查询)
     **/
    List<CurriculumUvipPriceBo> selectList(@Param("curriculumId") String curriculumId);


}