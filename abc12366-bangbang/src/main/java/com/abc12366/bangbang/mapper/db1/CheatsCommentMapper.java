package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.CheatsComment;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * CheatsCommentMapper数据库操作接口类
 * 
 **/

public interface CheatsCommentMapper{

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
	int insert(CheatsComment record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CheatsComment record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CheatsComment record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CheatsComment record);

	/**
	 *
	 * 修改被举报数+1,修改状态(根据主键ID)
	 *
	 **/
	int updateReportNum(@Param("id") String id);

}