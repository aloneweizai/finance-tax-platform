package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.question.bo.QuestionFactionTaskBo;

import java.util.List;
import java.util.Map;

public interface QueFactionTaskService {

    List<QuestionFactionTaskBo> selectListdt();

    List<QuestionFactionTaskBo> selectTaskList(Map map);

}
