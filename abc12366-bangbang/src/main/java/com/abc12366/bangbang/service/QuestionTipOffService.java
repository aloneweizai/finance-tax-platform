package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.question.QuestionTipOff;
import com.abc12366.bangbang.model.question.bo.QuestionTipOffBo;
import com.abc12366.bangbang.model.question.bo.QuestionTipOffParamBo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/9/19 11:48
 * 帮邦举报内容
 */
public interface QuestionTipOffService {

    /* 列表查询 */
    List<QuestionTipOffBo> selectList(QuestionTipOffParamBo paramBo);

    /* 举报详情 */
    QuestionTipOffBo selectOne(String id);

    /* 审核 */
    void changeStatus(QuestionTipOff questionTipOff);

    /* 后台审核 */
    void changeStatusByAdmin(QuestionTipOff questionTipOff, HttpServletRequest request);

    QuestionTipOffBo save(QuestionTipOffBo questionTipOffBo);

    QuestionTipOffBo savepb(QuestionTipOffBo questionTipOffBo);

    /* 根据状态查询数量 */
    Long selectCntByStatus(String status);
}
