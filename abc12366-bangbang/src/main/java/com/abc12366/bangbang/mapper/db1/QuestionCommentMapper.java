package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionComment;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionCommentMapper数据库操作接口类
 * 
 **/

public interface QuestionCommentMapper{

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
	int insert(QuestionComment record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionComment record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionComment record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionComment record);

	/**
	 *
	 * 修改被举报数+1(根据主键ID)
	 *
	 **/
	int updateReportNum(@Param("id") String id);

}