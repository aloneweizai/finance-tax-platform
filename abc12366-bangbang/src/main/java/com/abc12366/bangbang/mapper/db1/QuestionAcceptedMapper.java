package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionAccepted;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionAcceptedMapper数据库操作接口类
 * 
 **/

public interface QuestionAcceptedMapper{



	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int delete(@Param("id") String id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(QuestionAccepted record);


	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int update(QuestionAccepted record);


}