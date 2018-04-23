package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionMemberHonor;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionMemberHonorMapper数据库操作接口类
 * 
 **/

public interface QuestionMemberHonorMapper{

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
	int insert(QuestionMemberHonor record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionMemberHonor record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionMemberHonor record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionMemberHonor record);

}