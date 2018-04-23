package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionFactionAllocation;
import com.abc12366.bangbang.model.question.bo.QuestionFactionAllocationManageBo;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionFactionAllocationMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionAllocationMapper{

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
	int insert(QuestionFactionAllocation record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionFactionAllocation record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionFactionAllocation record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionFactionAllocation record);

	/**
	 *
	 * 审核
	 *
	 **/
	int audit(QuestionFactionAllocationManageBo record);

}