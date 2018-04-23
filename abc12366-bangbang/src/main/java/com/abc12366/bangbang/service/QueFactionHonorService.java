package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.question.bo.QuestionFactionPhBo;

import java.util.List;

public interface QueFactionHonorService {

    List<QuestionFactionPhBo> selectList(String honorTime);

    List<QuestionFactionPhBo> selectljList();

}
