package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.question.QuestionFactionLevel;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * QuestionFashionLevelMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionLevelMapper{


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
	int insert(QuestionFactionLevel record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(QuestionFactionLevel record);

}