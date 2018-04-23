package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.curriculum.bo.CurriculumCoursewareBo;

import java.util.List;
import java.util.Map;

public interface CoursewareService {

    List<CurriculumCoursewareBo> selectList(Map<String, Object> map);

    CurriculumCoursewareBo save(CurriculumCoursewareBo curriculumCoursewareBo);

    CurriculumCoursewareBo selectCourseware(String coursewareId);

    CurriculumCoursewareBo selectCoursewarebf(String coursewareId,String userId);

    CurriculumCoursewareBo update(CurriculumCoursewareBo curriculumCoursewareBo);

    String updateStatus(String coursewareId, String status);

    String delete(String coursewareId);

    List<CurriculumCoursewareBo> selectCoursewareList(Map<String, Object> dataMap);
}
