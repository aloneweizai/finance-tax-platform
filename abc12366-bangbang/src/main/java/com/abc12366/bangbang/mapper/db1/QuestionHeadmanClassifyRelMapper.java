package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionHeadmanClassifyRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * QuestionHeadmanClassifyRelMapper数据库操作接口类
 * 
 **/

public interface QuestionHeadmanClassifyRelMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionHeadmanClassifyRel selectByPrimaryKey(@Param("id") String id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("id") String id);

	/**
	 *
	 * 删除（根据HeadmanId删除）
	 *
	 **/
	int deleteByHeadmanId(@Param("id") String id);


	/**
	 * 批量添加
	 **/
	int insertBatch(List<QuestionHeadmanClassifyRel> list);

	/**
	 *
	 * 添加
	 *
	 **/
	int insert(QuestionHeadmanClassifyRel record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionHeadmanClassifyRel record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionHeadmanClassifyRel record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionHeadmanClassifyRel record);

}