package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.bo.TopicRecommendParamBO;
import com.abc12366.bangbang.model.question.Question;
import com.abc12366.bangbang.model.question.bo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionMapper数据库操作接口类
 * 
 **/

public interface QuestionRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	Question  selectByPrimaryKey(@Param("id") String id);

    /**
     *
     * 查询（根据主键ID查询）
     *
     **/
    QuestionBo  selectQuestion(@Param("id") String id);

    /**
     * 查询最新问题
     **/
    List<QuestionBo> selectList(Map<String, Object> map);

    /**
     * 推荐问题
     **/
    List<QuestionBo> selectListRecommend(Map<String, Object> map);

    /**
     * 高悬赏问题
     **/
    List<QuestionBo> selectListByPoints(Map<String, Object> map);

    /**
     * 查询热门问题
     **/
    List<QuestionBo> selectListByBrowseNum(Map<String, Object> map);

    /**
     * 查询等你回答的问题
     **/
    List<QuestionBo> selectListWait(Map<String, Object> map);

    /**
     * 查询已解决的问题
     **/
    List<QuestionBo> selectListAccept(Map<String, Object> map);

    /**
     * 查询邦友热议
     **/
    List<QuestionryBo> selectListry(Map<String, Object> map);

    /**
     * 我的举报
     **/
    List<QuestionjbBo> selectTipList(String userId);

    /**
     * 邀我回答
     **/
    List<QuestionBo> selectInviteList(String userId);

    /**
     * 我的帮帮
     **/
    MyQuestionTjBo selectMybangbang(String userId);

    /**
     * 查询我的提问
     **/
    List<QuestionBo> selectMyQuestionList(Map<String, Object> map);

    /**
     * 查询(话题推荐管理)
     **/
    List<RecommendBo> selectListTopicRecommend(TopicRecommendParamBO param);

    /**
     * 我管理的话题
     **/
    List<QuestionBo> selectMyManageQuesList(String userId);

    /**
     * 我的动态
     **/
    List<QuestionDtBo> selectQcDtList(String userId);

    /**
     * 查询分类代码
     **/
    String selectclassifyCode(String questionId);

    /**
     * 查询邦派ID
     **/
    String selectfactionId(Map<String, Object> map);

}