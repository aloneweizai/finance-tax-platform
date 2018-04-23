package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.bo.AnswerBO;
import com.abc12366.bangbang.model.bo.AnswerQueryParamBO;
import com.abc12366.bangbang.model.bo.AnswersQueryParamBO;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-12
 * Time: 16:32
 */
public interface AnswerRoMapper {
    List<AnswerBO> selectListForAdmin(AnswersQueryParamBO answersQueryParamBO);

    List<AnswerBO> selectListForUser(AnswerQueryParamBO answerQueryParamBO);

    AnswerBO selectOne(String id);
}
