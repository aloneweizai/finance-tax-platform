package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionFactionTag;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionFactionTagMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionTagMapper{

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("factionId") String factionId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(QuestionFactionTag record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionFactionTag record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionFactionTag record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionFactionTag record);

}