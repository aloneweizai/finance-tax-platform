package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionFactionMemberLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * QuestionFactionMemberLevelMapper数据库操作接口类
 * 
 **/

public interface QuestionFactionMemberLevelRoMapper {

	/**
	 *
	 * 查询列表
	 *
	 **/
	List<QuestionFactionMemberLevel> selectList();

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionFactionMemberLevel selectByPrimaryKey(@Param("id") String id);


}