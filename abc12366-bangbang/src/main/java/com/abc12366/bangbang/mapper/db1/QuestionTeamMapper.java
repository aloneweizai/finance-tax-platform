package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionTeam;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionTeamMapper数据库操作接口类
 * 
 **/

public interface QuestionTeamMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionTeam selectByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(QuestionTeam record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionTeam record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionTeam record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionTeam record);

}