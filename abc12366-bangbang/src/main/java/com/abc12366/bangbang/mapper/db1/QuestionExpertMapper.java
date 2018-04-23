package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionExpert;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionExpertMapper数据库操作接口类
 * 
 **/

public interface QuestionExpertMapper{


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
	int insert(QuestionExpert record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionExpert record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionExpert record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionExpert record);

}