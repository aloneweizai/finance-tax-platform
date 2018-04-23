package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.question.QuestionFactionLevel;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/9/22 14:37
 */
public interface QuestionFactionLevelService {

    List<QuestionFactionLevel> selectList();

    QuestionFactionLevel selectOne(String id);

    void add(QuestionFactionLevel record);

    void modify(QuestionFactionLevel record);

    void delete(String id);

}
