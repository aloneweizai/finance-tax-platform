package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionTag;
import com.abc12366.bangbang.model.question.bo.QuestionTagBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * QuestionTagMapper数据库操作接口类
 * 
 **/

public interface QuestionTagRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionTag  selectByPrimaryKey(@Param("id") String id);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionTag> selectList(@Param("questionId") String questionId);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    List<QuestionTagBo> selectTagList();




}