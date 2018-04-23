package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.bo.QuestionCommentBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionCommentMapper数据库操作接口类
 * 
 **/

public interface QuestionCommentRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionCommentBo selectByPrimaryKey(@Param("id") String id);

    /**
     * 查询(根据查询条件查询)
     **/
    List<QuestionCommentBo> selectList(Map<String, Object> map);

    /**
     * 查询我的评论
     **/
    List<QuestionCommentBo> selectMyCommentList(Map<String, Object> map);

    /**
     *
     * 查询评论数
     *
     **/
    int  selectCommentCnt(@Param("id") String id);

}