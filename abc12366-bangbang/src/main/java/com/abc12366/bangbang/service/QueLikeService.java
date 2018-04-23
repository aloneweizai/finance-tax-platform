package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.question.bo.QuestionBo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface QueLikeService {

    String insert(String id, HttpServletRequest request);

    String inserttrample(String id, HttpServletRequest request);

    String delete(String id, HttpServletRequest request);

    List<QuestionBo> selectList(String userId);

    String selectExist(String id, HttpServletRequest request);

}
