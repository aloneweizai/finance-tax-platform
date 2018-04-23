package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.curriculum.bo.CurriculumChapterBo;

import java.util.List;
import java.util.Map;

public interface ChapterService {

    List<CurriculumChapterBo> selectList(Map<String, Object> map);

    CurriculumChapterBo save(CurriculumChapterBo chapterBo);

    CurriculumChapterBo selectChapter(String chapterId);

    CurriculumChapterBo update(CurriculumChapterBo chapterBo);

    String updateStatus(String chapterId, String status);

    String delete(String chapterId);

}
