package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.question.QuestionFactionMemberLevel;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/9/22 14:44
 */
public interface QuestionFactionMemberLevelService {

    List<QuestionFactionMemberLevel> selectList();

    QuestionFactionMemberLevel selectOne(String id);

    void add(QuestionFactionMemberLevel record);

    void modify(QuestionFactionMemberLevel record);

    void delete(String id);

}
