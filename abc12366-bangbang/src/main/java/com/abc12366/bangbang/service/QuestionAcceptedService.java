package com.abc12366.bangbang.service;
import com.abc12366.bangbang.model.question.QuestionAccepted;
import com.abc12366.bangbang.model.question.bo.QuestionAcceptedBO;

import java.util.List;

/**
 * @Author lizhongwei
 * @Date 2017/9/21 13:39
 */
public interface QuestionAcceptedService {

    QuestionAccepted add(QuestionAccepted returnVisit);

    List<QuestionAccepted> selectList(QuestionAcceptedBO returnVisitBO);

    void delete(String id);

    List<QuestionAcceptedBO> selectStatisList(QuestionAcceptedBO param);

    /**
     * 问题受理 分页查询-后台
     * @param param
     * @return
     */
    List<QuestionAccepted> selectAdminList(QuestionAcceptedBO param);
}
