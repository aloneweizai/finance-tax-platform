package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.curriculum.CurriculumMembergrade;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * CurriculumMembergradeMapper数据库操作接口类
 * 
 **/

public interface CurriculumMembergradeMapper{

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
	int insert(CurriculumMembergrade record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CurriculumMembergrade record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CurriculumMembergrade record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CurriculumMembergrade record);

}