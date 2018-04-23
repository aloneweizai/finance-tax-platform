package com.abc12366.cms.service;


import com.abc12366.cms.model.bo.AnswerLogtjListBo;
import com.abc12366.cms.model.questionnaire.bo.AnswerLogBO;
import com.abc12366.cms.model.questionnaire.bo.AnswertjBO;

import java.util.List;
import java.util.Map;

/**
 * 答题记录管理接口类
 *
 * @author lizhongwei
 * @create 2017-6-14
 * @since 1.0.0
 */
public interface AnswerLogService {

    List<AnswerLogBO> selectList(AnswerLogBO answerLog);

    AnswerLogBO selectOne(String id);

    AnswerLogBO insert(AnswerLogBO answerLogBO);

    AnswerLogBO update(AnswerLogBO answerLogBO);

    void delete(AnswerLogBO answerLogBO);

    AnswerLogBO batch(AnswerLogBO answerLogBO);

    AnswerLogBO answerAvg(AnswerLogBO answerLog);

    AnswerLogtjListBo selecttj(Map<String, Object> map);

    List<AnswertjBO> selectListBysubjectsId(String subjectsId);

    int selectdtcnt(Map<String, Object> map);
}
