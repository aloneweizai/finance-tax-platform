package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.curriculum.CurriculumUvipPrice;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * CurriculumUvipPriceMapper数据库操作接口类
 * 
 **/

public interface CurriculumUvipPriceMapper{


	/**
	 *
	 * 删除（根据主键ID删除）
	 *
	 **/
	int deleteByPrimaryKey(@Param("curriculumId") String curriculumId);

	/**
	 *
	 * 添加
	 *
	 **/
	int insert(CurriculumUvipPrice record);

	/**
	 *
	 * 添加 （匹配有值的字段）
	 *
	 **/
	int insertSelective(CurriculumUvipPrice record);

	/**
	 *
	 * 修改 （匹配有值的字段）
	 *
	 **/
	int updateByPrimaryKeySelective(CurriculumUvipPrice record);

	/**
	 *
	 * 修改（根据主键ID修改）
	 *
	 **/
	int updateByPrimaryKey(CurriculumUvipPrice record);

}