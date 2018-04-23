package com.abc12366.bangbang.service;



import com.abc12366.bangbang.model.question.bo.QuestionFactionAllocationBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionAllocationManageBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionAllocationsBo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface QueFactionAllocationService {

    List<QuestionFactionAllocationBo> selectList(Map<String, Object> map);

    QuestionFactionAllocationBo save(QuestionFactionAllocationBo questionFactionAllocationBo);

    QuestionFactionAllocationBo selectFactionAllocation(String id);

    QuestionFactionAllocationBo update(QuestionFactionAllocationBo questionFactionAllocationBo);

    String delete(String id);

    List<QuestionFactionAllocationManageBo> selectManageList(Map<String, Object> map);

    List<QuestionFactionAllocationsBo> selectAllocationList(Map<String, Object> map);

    void audit(List<QuestionFactionAllocationManageBo> records, HttpServletRequest request);

}
