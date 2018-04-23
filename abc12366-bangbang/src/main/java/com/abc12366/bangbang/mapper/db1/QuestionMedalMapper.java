package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionMedal;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionMedalMapper数据库操作接口类
 * 
 **/

public interface QuestionMedalMapper{

	/**
	 *
	 * 删除（根据主键ID删除）
	 *
	 **/
	int deleteByPrimaryKey(@Param("id") String id);

	/**
	 *
	 * 添加
	 *
	 **/
	int insert(QuestionMedal record);

	/**
	 *
	 * 添加 （匹配有值的字段）
	 *
	 **/
	int insertSelective(QuestionMedal record);

	/**
	 *
	 * 修改 （匹配有值的字段）
	 *
	 **/
	int updateByPrimaryKeySelective(QuestionMedal record);

	/**
	 *
	 * 修改（根据主键ID修改）
	 *
	 **/
	int updateByPrimaryKey(QuestionMedal record);

}