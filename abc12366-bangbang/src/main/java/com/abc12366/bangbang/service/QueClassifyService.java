package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.question.QuestionClassifyStatistics;
import com.abc12366.bangbang.model.question.bo.QuestionClassifyBo;
import com.abc12366.bangbang.model.question.bo.QuestionClassifyTagBo;

import java.util.List;
import java.util.Map;

public interface QueClassifyService {

    List<QuestionClassifyBo> selectList(Map<String, Object> map);

    QuestionClassifyBo save(QuestionClassifyBo classifyBo);

    QuestionClassifyBo selectClassify(String classifyCode);

    List<QuestionClassifyTagBo> selectClassifyTagList(String classifyCode);

    QuestionClassifyBo update(QuestionClassifyBo classifyBo);

    String updateStatus(String classifyCode, String status);

    String delete(String classifyCode);

    /*修改分类标签关联关系*/
    void updateClassifyTag(QuestionClassifyBo classifyBo);


    List<QuestionClassifyStatistics> selectClassifyStatistics(Map<String, Object> map);
}
