package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.question.bo.QuestionAnswerBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionListBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionTjBo;

import java.util.List;
import java.util.Map;

public interface QueFactionService {

    List<QuestionFactionBo> selectList(Map<String, Object> map);

    List<QuestionFactionListBo> selectListTj(Map<String, Object> map);

    List<QuestionFactionListBo> selectListExcellent(Map<String, Object> map);

    List<QuestionFactionListBo> selectListPotential(Map<String, Object> map);

    QuestionFactionBo save(QuestionFactionBo factionBo);

    QuestionFactionBo selectQuestionFaction(String factionId);

    QuestionFactionTjBo selectQuestionFactionTj(String factionId);

    List<QuestionAnswerBo> selectdtListByFactionId(String factionId);

    QuestionFactionBo update(QuestionFactionBo factionBo);

    QuestionFactionBo updateSelect(QuestionFactionBo factionBo);

    String updateStatus(String factionId, String status);

    String delete(String factionId);

    void autoCalculateFactionHonor();

}
