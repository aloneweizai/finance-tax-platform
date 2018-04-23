package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.QuestionAttention;
import com.abc12366.bangbang.model.question.bo.QuestionAttentionBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * QuestionAttentionMapper数据库操作接口类
 * 
 **/

public interface QuestionAttentionRoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	QuestionAttention selectByPrimaryKey(@Param("attentionId") String attentionId);

    int selectExist(Map map);

    int selectAttentionCnt(String attentionUserId);

    /**
     *
     * 查询我关注的用户
     *
     **/
    List<QuestionAttentionBo> selectAttentionUserList(@Param("userId") String userId);

    /**
     *
     * 查询关注我的用户
     *
     **/
    List<QuestionAttentionBo> selectUserList(@Param("attentionUserId") String attentionUserId);

}