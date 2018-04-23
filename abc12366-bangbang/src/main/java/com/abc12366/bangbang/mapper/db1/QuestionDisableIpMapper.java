package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionDisableIp;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionDisableIpMapper数据库操作接口类
 * 
 **/

public interface QuestionDisableIpMapper{


	/**
	 *
	 * 删除（根据主键ID删除）
	 *
	 **/
	int deleteByPrimaryKey(@Param("id") String id);

	/**
	 *
	 * 删除（根据主键ID删除）
	 *
	 **/
	int deleteByIP(@Param("ip") String ip);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(QuestionDisableIp record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionDisableIp record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionDisableIp record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionDisableIp record);

}