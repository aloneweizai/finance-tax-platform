package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.curriculum.CurriculumEvaluate;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * CurriculumEvaluateMapper数据库操作接口类
 * 
 **/

public interface CurriculumEvaluateMapper{


	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("evaluateId") String evaluateId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(CurriculumEvaluate record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CurriculumEvaluate record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CurriculumEvaluate record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CurriculumEvaluate record);

}