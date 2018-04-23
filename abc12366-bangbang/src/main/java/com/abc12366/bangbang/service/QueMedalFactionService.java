package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.question.bo.QuestionMedalFactionBo;

import java.util.List;
import java.util.Map;

public interface QueMedalFactionService {

    List<QuestionMedalFactionBo> selectListByFactionId(Map<String, Object> map);


}
