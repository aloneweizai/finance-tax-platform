package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.question.bo.QuestionCountBo;

import java.util.List;


/**
 * 
 * QuestionLikeMapper数据库操作接口类
 * 
 **/

public interface QuestionCountRoMapper {
    List<QuestionCountBo> selectLike();
    List<QuestionCountBo> selectAttention();
    List<QuestionCountBo> selectAccept();

    List<QuestionCountBo> selectAnswers();
    List<QuestionCountBo> selectMedal();
    List<QuestionCountBo> selectAcceptExpert();
}