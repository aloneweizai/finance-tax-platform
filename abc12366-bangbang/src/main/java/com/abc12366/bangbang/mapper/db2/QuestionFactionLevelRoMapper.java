package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionFactionLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * QuestionFashionLevelMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionLevelRoMapper {


	/**
	 *
	 * 查询列表
	 *
	 **/
	List<QuestionFactionLevel> selectList();

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionFactionLevel selectByPrimaryKey(@Param("id") String id);


}