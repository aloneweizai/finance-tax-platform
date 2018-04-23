package com.abc12366.uc.service;

import com.abc12366.uc.model.Check;
import com.abc12366.uc.model.CheckRank;
import com.abc12366.uc.model.ReCheck;
import com.abc12366.uc.model.bo.CheckListBO;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-21
 * Time: 14:28
 */
public interface CheckService {
    /**
     * 用户签到接口
     * @param check {@linkplain com.abc12366.uc.model.Check}
     * @return  签到获取的积分值
     */
    int check(Check check);

    /**
     * 用户补签接口
     * @param check {@linkplain com.abc12366.uc.model.ReCheck}
     */
    void reCheck(ReCheck check);

    /**
     * 用户签到排行列表
     * @param year 年份
     * @return List<CheckRank> {@linkplain com.abc12366.uc.model.CheckRank}
     */
    List<CheckRank> rank(String year);

    /**
     * 获取用户的签到情况
     * @param yearMonth 年月：2017-09
     * @return  {@linkplain com.abc12366.uc.model.bo.CheckListBO}
     */
    List<CheckListBO> checklist(String yearMonth);

    /**
     * 查询用户本年度累计签到天数
     * @param userId 用户ID
     * @return 用户年度累计签到天数
     */
    int checkTotal(String userId);
}
