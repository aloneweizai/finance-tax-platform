package com.abc12366.uc.service;

import com.abc12366.uc.model.bo.VipLevelStatisticTemp;
import com.abc12366.uc.model.bo.VipLogBO;
import com.abc12366.uc.model.bo.VipLogOrderBO;

import java.util.List;
import java.util.Map;

/**
 * VIP日志服务
 *
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-19
 * Time: 15:37
 */
public interface VipLogService {

    /**
     * 查询会员日志
     * @param userId 用户ID
     * @return {@linkplain com.abc12366.uc.model.bo.VipLogBO VipLogBO}日志对象 List
     */
    List<VipLogBO> selectList(String userId);

    /**
     * 新增会员日期，会员期限加1年，普通用户（VIP0）为10年
     *
     * @param vipLogBO 日志对象
     * @param type 1：会员充值，2：会员退订
     * @return {@linkplain com.abc12366.uc.model.bo.VipLogBO VipLogBO}日志对象
     */
    VipLogBO insert(VipLogBO vipLogBO,int type);

    /**
     * 根据编码查询会员日志列表
     * @param map
     * @return
     */
    VipLevelStatisticTemp selectCountByCode(Map map);

    /**
     * 查询当前所有该会员等级用户总数
     * @param levelCode
     * @return
     */
    int selectCountAll(String levelCode);

    /**
     * 会员日志列表
     * @param userId
     * @return
     */
    List<VipLogOrderBO> selectListByOrder(String userId);
}
