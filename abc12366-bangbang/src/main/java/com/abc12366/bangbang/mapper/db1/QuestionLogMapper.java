package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionLog;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionLikeMapper数据库操作接口类
 * 
 **/

public interface QuestionLogMapper {


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
	int insert(QuestionLog record);

}