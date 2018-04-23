package com.abc12366.cms.service;


import com.abc12366.cms.model.bo.SubjectsdtxxtjBo;
import com.abc12366.cms.model.questionnaire.Subjects;
import com.abc12366.cms.model.questionnaire.bo.SubjectsBO;

import java.util.List;
import java.util.Map;

/**
 * 题目管理接口类
 *
 * @author lizhongwei
 * @create 2017-6-14
 * @since 1.0.0
 */
public interface SubjectsService {

    List<SubjectsBO> selectList(SubjectsBO subjects);

    SubjectsBO selectOne(String id);

    SubjectsBO insert(SubjectsBO subjectsBO);

    SubjectsBO update(SubjectsBO subjectsBO);

    void delete(SubjectsBO subjectsBO);

    List<SubjectsBO> insertList(List<SubjectsBO> subjectsBOs, String questionId);

    List<SubjectsBO> updateList(List<SubjectsBO> subjectsBOs, String questionId, String id);

    List<SubjectsBO> deleteList(List<SubjectsBO> subjectsBOs, String questionId, String id);

    SubjectsBO copySubjects(List<SubjectsBO> subjectsBOs, String subjectsId);

    void deleteSubjectsByPages(Subjects subjects);

    List<SubjectsdtxxtjBo> selectListdttj(Map<String, Object> map);
}
