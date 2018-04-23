package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionFactionClassify;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionFactionClassifyMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionClassifyMapper{

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
	int insert(QuestionFactionClassify record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionFactionClassify record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionFactionClassify record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionFactionClassify record);

}