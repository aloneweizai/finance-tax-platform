package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.curriculum.CurriculumApply;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * CurriculumApplyMapper数据库操作接口类
 * 
 **/

public interface CurriculumApplyMapper{


	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("applyId") String applyId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(CurriculumApply record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CurriculumApply record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CurriculumApply record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CurriculumApply record);

}