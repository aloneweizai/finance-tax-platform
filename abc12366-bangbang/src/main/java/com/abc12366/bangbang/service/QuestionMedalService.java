package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.question.QuestionMedal;
import com.abc12366.bangbang.model.question.bo.QuestionMedalBo;

import java.util.List;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/10/20 17:54
 */
public interface QuestionMedalService {

    List<QuestionMedalBo> selectList(Map map);

    QuestionMedal selectOne(String id);

    void add(QuestionMedal medal);

    void modify(QuestionMedal medal);

    void delete(String id);

}
