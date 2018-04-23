package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.question.bo.QuestionCountBo;

import java.util.List;

public interface QueCountService {
    List<QuestionCountBo> selectLike();
    List<QuestionCountBo> selectAttention();

    List<QuestionCountBo> selectAccept();
    List<QuestionCountBo> selectAnswers();
    List<QuestionCountBo> selectMedal();
    List<QuestionCountBo> selectAcceptExpert();
}
