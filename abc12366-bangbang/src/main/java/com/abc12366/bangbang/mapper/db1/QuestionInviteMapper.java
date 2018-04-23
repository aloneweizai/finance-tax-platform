package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionInvite;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionInviteMapper数据库操作接口类
 * 
 **/

public interface QuestionInviteMapper{

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("questionId") String questionId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(QuestionInvite record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(QuestionInvite record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionInvite record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(QuestionInvite record);

    /**
     *
     * 修改为已读(根据主键ID)
     *
     **/
    int updateIsRead(QuestionInvite record);


}