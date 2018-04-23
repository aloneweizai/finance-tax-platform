package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.question.CheatsSearchBo;
import com.abc12366.bangbang.model.question.QuestionSearchBo;

import java.util.List;

/**
 * Created by stuy on 2017/11/9.
 */
public interface SearchService {

    List<QuestionSearchBo> queryQuestionSearch(String text);

    List<CheatsSearchBo> queryCheatsSearch(String text);

}
