package com.abc12366.bangbang.service;

import com.abc12366.bangbang.model.question.QuestionFactionRewardSetting;
import com.abc12366.bangbang.model.question.bo.QuestionFactionRewardSettingBo;
import com.abc12366.bangbang.model.question.bo.QuestionFactionRewardSettingParamBo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/10/28 15:30
 */
public interface QuestionFactionRewardSettingService {

    /*列表查询*/
    List<QuestionFactionRewardSettingBo> selectFactionRewardSettingList(QuestionFactionRewardSettingParamBo param);

    /*分配积分给帮派*/
    void setting(QuestionFactionRewardSetting record, HttpServletRequest request);

}
