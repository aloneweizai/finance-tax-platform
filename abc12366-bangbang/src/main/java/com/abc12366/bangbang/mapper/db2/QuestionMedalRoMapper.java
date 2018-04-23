package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionMedal;
import com.abc12366.bangbang.model.question.bo.QuestionMedalBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionMedalMapper数据库操作接口类
 * 
 **/

public interface QuestionMedalRoMapper {

	/**
	 *
	 * 列表查询
	 *
	 **/
	List<QuestionMedalBo> selectList(Map map);




	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionMedal selectByPrimaryKey(@Param("id") String id);

}