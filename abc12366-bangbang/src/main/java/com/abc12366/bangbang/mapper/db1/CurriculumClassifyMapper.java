package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.curriculum.CurriculumClassify;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * CurriculumChapterMapper数据库操作接口类
 * 
 **/

public interface CurriculumClassifyMapper {


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
	int insert(CurriculumClassify record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CurriculumClassify record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CurriculumClassify record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CurriculumClassify record);

}