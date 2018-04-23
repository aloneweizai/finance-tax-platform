package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.question.QuestionExpert;
import com.abc12366.bangbang.model.question.bo.QuestionExpertBO;
import com.abc12366.bangbang.model.question.bo.QuestionExpertParamBo;

import java.util.List;
import java.util.Map;

/**
 * @Author liuQi
 * @Date 2017/9/14 18:39
 */
public interface QuestionExpertService {

    /* 查询用户列表 */
    List<QuestionExpertBO> selectList(QuestionExpertParamBo param);

    /* 查询大侠列表 */
    List<QuestionExpertBO> selectListDX(Map<String, Object> map);

    /* 单个查询 */
    QuestionExpertBO selectOne(String id);

    /* 新增专家 */
    void add(QuestionExpert questionExpert);

    /* 修改专家 */
    void modify(QuestionExpert questionExpert);

    /* 删去专家 */
    void delete(String id);

    /* 根据userID查询专家列表 */
    List<QuestionExpertBO> selectListByUserId(String userId);

}
