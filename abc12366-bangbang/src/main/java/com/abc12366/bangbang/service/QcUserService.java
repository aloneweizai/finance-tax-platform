package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.question.bo.QcBangUserBo;
import com.abc12366.bangbang.model.question.bo.QcTitleBo;

import java.util.List;

public interface QcUserService {

    List<QcBangUserBo> selectList();

    List<QcTitleBo> selectQuestionList(String userId);

}
