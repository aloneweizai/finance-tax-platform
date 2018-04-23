package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.curriculum.CurriculumClassifyTag;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionClassifyTagMapper数据库操作接口类
 * 
 **/

public interface CurriculumClassifyTagMapper {


	/**
	 *
	 * 删除（根据主键ID删除）
	 *
	 **/
	int deleteByPrimaryKey(@Param("classifyId") String classifyId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(CurriculumClassifyTag record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CurriculumClassifyTag record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CurriculumClassifyTag record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CurriculumClassifyTag record);

}