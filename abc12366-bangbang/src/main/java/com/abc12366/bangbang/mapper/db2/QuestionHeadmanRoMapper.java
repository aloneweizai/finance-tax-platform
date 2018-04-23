package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionHeadman;
import com.abc12366.bangbang.model.question.bo.QuestionHeadmanBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * QuestionHeadmanMapper数据库操作接口类
 * 
 **/

public interface QuestionHeadmanRoMapper {

	/**
	 *
	 * 列表查询
	 *
	 **/
	List<QuestionHeadman> selectList();

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionHeadmanBo selectByPrimaryKey(@Param("id") String id);

    int selectExist(String userId);


}