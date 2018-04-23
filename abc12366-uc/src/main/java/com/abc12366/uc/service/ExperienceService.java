package com.abc12366.uc.service;

import com.abc12366.uc.model.bo.ExpCalculateBO;
import com.abc12366.uc.model.bo.ExpCodex;
import com.abc12366.uc.model.bo.ExpComputeBO;
import com.abc12366.uc.model.bo.MyExperienceBO;

import java.util.List;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-25
 * Time: 16:33
 */
public interface ExperienceService {

    /**
     * 查询用户经验值概况
     * @param userId 用户ID
     * @return MyExperienceBO {@linkplain com.abc12366.uc.model.bo.MyExperienceBO}
     */
    MyExperienceBO getMyExperience(String userId);

    List<ExpCodex> codex(String uexpruleId, List<ExpCodex> codex);

    int deleteCodex(String id);

    List<ExpCodex> codexList();

    void compute(ExpComputeBO expComputeBO);

    /**
     * 根据经验值规则计算用户的经验值变化，并记日志
     * @param expCalculateBO {@linkplain com.abc12366.uc.model.bo.ExpCalculateBO}
     */
    void calculate(ExpCalculateBO expCalculateBO);
}
