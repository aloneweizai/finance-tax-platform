package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.curriculum.CurriculumStudy;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * CurriculumStudyMapper数据库操作接口类
 * 
 **/

public interface CurriculumStudyMapper{

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("studyId") String studyId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(CurriculumStudy record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CurriculumStudy record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CurriculumStudy record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CurriculumStudy record);

}