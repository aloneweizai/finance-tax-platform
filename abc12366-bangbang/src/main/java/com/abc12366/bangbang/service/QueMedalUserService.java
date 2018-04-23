package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.question.bo.QuestionMedalUserBo;

import java.util.List;
import java.util.Map;

public interface QueMedalUserService {

    List<QuestionMedalUserBo> selectListByUserId(Map<String, Object> map);


}
