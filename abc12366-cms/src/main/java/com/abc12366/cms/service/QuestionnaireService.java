package com.abc12366.cms.service;


import com.abc12366.cms.model.questionnaire.bo.QuestionnaireBO;

import java.util.List;

/**
 * 问卷管理接口类
 *
 * @author lizhongwei
 * @create 2017-6-15
 * @since 1.0.0
 */
public interface QuestionnaireService {

    List<QuestionnaireBO> selectList(QuestionnaireBO questionnaire);

    QuestionnaireBO selectOne(String id);

    QuestionnaireBO insert(QuestionnaireBO questionnaireBO);

    QuestionnaireBO update(QuestionnaireBO questionnaireBO);

    void delete(QuestionnaireBO questionnaireBO);

    void updateStatus(String id, String status);

    void updateSkinUrl(String id, String skinUrl);

    void updateAccessRate(String id);

    void updateRecoveryRate(String id);

    QuestionnaireBO copy(QuestionnaireBO questionnaireBO);

    QuestionnaireBO selectAccessNum(String id);
}
