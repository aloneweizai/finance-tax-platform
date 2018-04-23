package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.Answer;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-12
 * Time: 14:36
 */
public interface AnswerMapper {

    int insert(Answer answer);

    int update(Answer answer);

    int delete(String id);
}
