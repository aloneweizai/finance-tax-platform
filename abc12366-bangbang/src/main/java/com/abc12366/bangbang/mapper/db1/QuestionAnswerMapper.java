package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionAnswer;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionAnswerMapper数据库操作接口类
 * 
 **/

public interface QuestionAnswerMapper{

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
	int insert(QuestionAnswer record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionAnswer record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionAnswer record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionAnswer record);

	/**
	 *
	 * 修改被举报数+1(根据主键ID)
	 *
	 **/
	int updateReportNum(@Param("id") String id);

    /**
     *
     * 修改为已读(根据主键ID)
     *
     **/
    int updateIsRead(@Param("id") String id);

    /**
     *
     * 设置为采纳
     *
     **/
    int updateIsAccept(@Param("id") String id);

}