package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionClassify;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionClassifyMapper数据库操作接口类
 * 
 **/

public interface QuestionClassifyMapper{


	/**
	 *
	 * 删除（根据主键ID删除）
	 *
	 **/
	int deleteByPrimaryKey(@Param("classifyCode") String classifyCode);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(QuestionClassify record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionClassify record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionClassify record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionClassify record);

}