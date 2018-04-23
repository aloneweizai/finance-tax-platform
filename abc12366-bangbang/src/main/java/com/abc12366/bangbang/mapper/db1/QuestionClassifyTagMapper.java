package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionClassifyTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * QuestionClassifyTagMapper数据库操作接口类
 * 
 **/

public interface QuestionClassifyTagMapper{


	/**
	 *
	 * 删除（根据主键ID删除）
	 *
	 **/
	int deleteByPrimaryKey(@Param("classifyId") String classifyId);

	/**
	 *
	 * 删除
	 *
	 **/
	int deleteByTagId(@Param("tagId") String tagId);

	/**
	 *
	 * 删除
	 *
	 **/
	int deleteByTagIds(List<String> tagIds);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(QuestionClassifyTag record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionClassifyTag record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionClassifyTag record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionClassifyTag record);

}