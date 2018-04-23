package com.abc12366.bangbang.service;


import com.abc12366.bangbang.model.question.bo.CheatsCommentBo;

import java.util.List;
import java.util.Map;

public interface CheatsCommentService {

    List<CheatsCommentBo> selectList(Map<String, Object> map);

    CheatsCommentBo save(CheatsCommentBo cheatsCommentBo);

    CheatsCommentBo selectComment(String id);

    CheatsCommentBo update(CheatsCommentBo cheatsCommentBo);

    String updateStatus(String id, String status);

    String delete(String id);

    List<CheatsCommentBo> selectMyCommentList(Map<String, Object> map);

}
