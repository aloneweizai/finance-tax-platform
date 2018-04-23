package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.bo.QuestionAnswerBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionAnswerMapper数据库操作接口类
 * 
 **/

public interface QuestionAnswerRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
    QuestionAnswerBo  selectByPrimaryKey(@Param("id") String id);

    /**
     * 查询(根据查询条件查询)
     **/
    List<QuestionAnswerBo> selectList(Map<String, Object> map);

    /**
     * 查询(根据查询条件查询)
     **/
    List<QuestionAnswerBo> selectListNew(Map<String, Object> map);

    /**
     * 查询(根据查询条件查询)
     **/
    String selectfactionId(Map<String, Object> map);

    /**
     * 查询我的回答
     **/
    List<QuestionAnswerBo> selectMyAnswerList(Map<String, Object> map);

    /**
     *
     * 查询回复数
     *
     **/
    int  selectAnswerCnt(@Param("questionId") String questionId);

    /**
     *
     * 查询是否已回复
     *
     **/
    int  selectMyAnswerCnt(Map<String, Object> map);

    /**
     *
     * 查询是否自己回答的
     *
     **/
    int  selectIsMyAnswer(Map<String, Object> map);

    /**
     *
     * 查询问题是否采纳过
     *
     **/
    int selectAcceptCnt(@Param("questionId") String questionId);


}