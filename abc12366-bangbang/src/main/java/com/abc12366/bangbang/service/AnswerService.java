package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.bo.*;

import java.io.IOException;
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-12
 * Time: 11:42
 */
public interface AnswerService {
    AnswerBO insert(AnswerInsertBO answerInserBO);

    AnswerBO update(String id, AnswerUpdateBO answerUpdateBO, String userId);

    int delete(String id, String userId);

    List<AnswerBO> selectListForAdmin(AnswersQueryParamBO answersQueryParamBO);

    List<AnswerBO> selectListForUser(AnswerQueryParamBO answerQueryParamBO);

    int block(String id, String userId);

    AnswerBO selectOne(String id);

    int accept(String id, String userId) throws IOException;
}
