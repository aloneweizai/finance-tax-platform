package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.curriculum.bo.CurriculumStudyBo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface CurrStudyService {

    List<CurriculumStudyBo> selectList(Map<String, Object> map);

    CurriculumStudyBo save(CurriculumStudyBo curriculumStudyBo,HttpServletRequest request);

    CurriculumStudyBo selectStudy(String studyId);

    CurriculumStudyBo update(CurriculumStudyBo curriculumStudyBo);

    String updateStatus(String studyId, String status);

    String delete(String studyId);

    int selectStudyCnt(Map<String, Object> map);

}
