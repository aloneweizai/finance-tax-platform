package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.curriculum.CurriculumLecturerGx;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * CurriculumLecturerMapper数据库操作接口类
 * 
 **/

public interface CurriculumLecturerGxMapper {

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
	int insert(CurriculumLecturerGx record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CurriculumLecturerGx record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CurriculumLecturerGx record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CurriculumLecturerGx record);

}