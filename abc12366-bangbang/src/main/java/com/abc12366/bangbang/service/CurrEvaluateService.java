package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.curriculum.bo.CurriculumEvaluateBo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface CurrEvaluateService {

    List<CurriculumEvaluateBo> selectList(Map<String, Object> map);

    List<CurriculumEvaluateBo> selectListBycurrId(Map<String, Object> map);

    CurriculumEvaluateBo save(CurriculumEvaluateBo curriculumEvaluateBo,HttpServletRequest request);

    CurriculumEvaluateBo selectEvaluate(String evaluateId);

    CurriculumEvaluateBo update(CurriculumEvaluateBo curriculumEvaluateBo);

    String updateStatus(String evaluateId, String status);

    String delete(String evaluateId);

    int selectEvaluateCnt(Map<String, Object> map);

}
