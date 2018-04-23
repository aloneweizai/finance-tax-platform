package com.abc12366.bangbang.service;



import com.abc12366.bangbang.model.question.bo.QuestionFactionInformBo;

import java.util.List;
import java.util.Map;

public interface QueFactionInformService {

    List<QuestionFactionInformBo> selectList(Map<String, Object> map);

    QuestionFactionInformBo save(QuestionFactionInformBo questionFactionInformBo);

    QuestionFactionInformBo selectFactionInform(String id);

    QuestionFactionInformBo update(QuestionFactionInformBo questionFactionInformBo);

    String delete(String id);

}
