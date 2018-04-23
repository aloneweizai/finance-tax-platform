package com.abc12366.bangbang.service;



import com.abc12366.bangbang.model.question.bo.QuestionFactionMemberBo;

import java.util.List;
import java.util.Map;

public interface QueFactionMemberService {

    List<QuestionFactionMemberBo> selectList(Map<String, Object> map);

    QuestionFactionMemberBo save(QuestionFactionMemberBo questionFactionMemberBo);

    QuestionFactionMemberBo selectFactionMember(String memberId);

    List<QuestionFactionMemberBo> selectListTj(Map<String, Object> map);

    QuestionFactionMemberBo update(QuestionFactionMemberBo questionFactionMemberBo);

    String updateStatus(String memberId, String status);

    String updateDuty(String memberId, String duty);

    String delete(String memberId);

}
