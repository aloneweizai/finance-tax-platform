package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionTag;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionTagMapper数据库操作接口类
 * 
 **/

public interface QuestionTagMapper{

	/**
	 *
	 * 删除（根据主键ID删除）
	 *
	 **/
	int deleteByPrimaryKey(@Param("questionId") String questionId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(QuestionTag record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionTag record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionTag record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionTag record);

}