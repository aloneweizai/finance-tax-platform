package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.question.bo.QuestionAttentionBo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface QueAttentionService {

    String insert(String id, HttpServletRequest request);

    String delete(String id, HttpServletRequest request);

    List<QuestionAttentionBo> selectAttentionUserList(String userId);

    List<QuestionAttentionBo> selectUserList(String attentionUserId);

    String selectExist(String id, HttpServletRequest request);

    String updateIsRead(String id);

}
