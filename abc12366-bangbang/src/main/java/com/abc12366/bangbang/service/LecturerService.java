package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.curriculum.bo.CurriculumLecturerBo;

import java.util.List;
import java.util.Map;

public interface LecturerService {

    List<CurriculumLecturerBo> selectList(Map<String, Object> map);

    List<CurriculumLecturerBo> selectListByCurr(String curriculumId);

    CurriculumLecturerBo save(CurriculumLecturerBo lecturerBo);

    CurriculumLecturerBo selectLecturer(String lecturerId);

    CurriculumLecturerBo update(CurriculumLecturerBo lecturerBo);

    String updateStatus(String lecturerId, String status);

    String delete(String lecturerId);

}
