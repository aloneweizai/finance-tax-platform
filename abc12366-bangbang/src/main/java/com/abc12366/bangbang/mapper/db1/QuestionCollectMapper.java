package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionCollect;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 
 * QuestionCollectMapper数据库操作接口类
 * 
 **/

public interface QuestionCollectMapper{

	/**
	 *
	 * 删除（根据主键ID删除）
	 *
	 **/
	int deleteByPrimaryKey(@Param("collectId") String collectId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(QuestionCollect record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionCollect record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionCollect record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionCollect record);

    void delete(Map map);

}