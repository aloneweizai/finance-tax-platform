package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.ExperienceLogMapper;
import com.abc12366.uc.mapper.db1.UserMapper;
import com.abc12366.uc.mapper.db2.ExperienceLogRoMapper;
import com.abc12366.uc.model.ExperienceLog;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.*;
import com.abc12366.uc.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 15:07
 */
@Service
public class ExperienceLogServiceImpl implements ExperienceLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExperienceLogServiceImpl.class);

    @Autowired
    private ExperienceLogRoMapper experienceLogRoMapper;

    @Autowired
    private ExperienceLogMapper experienceLogMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private PointsLogService pointsLogService;

    @Autowired
    private PointsRuleService pointsRuleService;

    @Autowired
    private UserFeedbackMsgService userFeedbackMsgService;

    @Autowired
    private VipPrivilegeLevelService vipPrivilegeLevelService;

    @Transactional("db1TxManager")
    @Override
    public ExperienceLogQueryBO insert(ExperienceLogBO experienceLogBO) {
        if (experienceLogBO == null) {
            LOGGER.warn("新增失败,入参为空,参数为：" + null);
            throw new ServiceException(4101);
        }

        User user = userMapper.selectOne(experienceLogBO.getUserId());
        if (user == null) {
            LOGGER.warn("新增失败,userId为不存在用户的id,参数为：userId=" + experienceLogBO.getUserId());
            throw new ServiceException("23333", "新增失败,不存在的用户Id=" + experienceLogBO.getUserId());
        }

        //会员权限埋点（经验值加成）
        if (!StringUtils.isEmpty(user.getVipLevel()) && (experienceLogBO.getIncome() - experienceLogBO.getOutgo() > 0)) {
            VipPrivilegeLevelBO vipPrivilegeLevelBOPar = new VipPrivilegeLevelBO();
            vipPrivilegeLevelBOPar.setLevelId(user.getVipLevel());
            vipPrivilegeLevelBOPar.setPrivilegeId("A_YHDJJS");
            VipPrivilegeLevelBO vipPrivilegeLevelBO = vipPrivilegeLevelService.selectLevelIdPrivilegeId(vipPrivilegeLevelBOPar);
            if (vipPrivilegeLevelBO != null && vipPrivilegeLevelBO.getStatus()) {
                if (!StringUtils.isEmpty(vipPrivilegeLevelBO.getVal1())) {
                    LOGGER.info("会员等级提速：{}", vipPrivilegeLevelBO.getVal1()+"倍");
                    try{
                        experienceLogBO.setIncome((int) ((experienceLogBO.getIncome() - experienceLogBO.getOutgo()) * Float.parseFloat(vipPrivilegeLevelBO.getVal1())));
                    }catch (Exception e){
                        LOGGER.error("经验值加成失败：{}",e);
                    }
                }
            }
        }

        //可用经验值=上一次的可用经验值+|-本次收入|支出
        int preExp = 0;
        if (user.getExp() != null) {
            preExp = user.getExp();
        }
        int usableExp = preExp + experienceLogBO.getIncome() - experienceLogBO.getOutgo();

        //uc_user的exp字段和uc_uexp_log的usableExp字段都要更新
        user.setExp(usableExp);
        user.setLastUpdate(new Date());
        int userUpdateResult = userMapper.updateExp(user);
        if (userUpdateResult != 1) {
            LOGGER.warn("新增失败,更新用户表经验值失败,参数为：userId=" + experienceLogBO.getUserId());
            throw new ServiceException(4101);
        }

        //用户升级奖励埋点
        expLevelUpgradeAward(user.getId(), usableExp);

        ExperienceLog experienceLog = new ExperienceLog();
        BeanUtils.copyProperties(experienceLogBO, experienceLog);
        experienceLog.setId(Utils.uuid());
        experienceLog.setCreateTime(new Date());
        experienceLog.setUsableExp(usableExp);
        int result = experienceLogMapper.insert(experienceLog);
        if (result != 1) {
            LOGGER.warn("新增失败,参数为：" + experienceLog);
            throw new ServiceException(4101);
        }

        ExperienceLogQueryBO experienceLogReturn = new ExperienceLogQueryBO();
        BeanUtils.copyProperties(experienceLog, experienceLogReturn);
        return experienceLogReturn;
    }

    private void expLevelUpgradeAward(String userId, int newExp) {
        if (StringUtils.isEmpty(userId)) {
            return;
        }
        User user = userMapper.selectOne(userId);
        if (user == null || user.getExp() == null) {
            return;
        }

        MyExperienceBO myExperienceBO = experienceService.getMyExperience(userId);
        if (myExperienceBO == null || StringUtils.isEmpty(myExperienceBO.getNextLevelExp())) {
            return;
        }

        if (newExp >= Integer.parseInt(myExperienceBO.getNextLevelExp())) {
            //如果积分规则为空则返回
            PointsRuleBO pointsRuleBO = pointsRuleService.selectValidOneByCode(TaskConstant.POINT_RULE_EXP_UP_CODE);
            if (pointsRuleBO == null) {
                return;
            }

            //会员特权
            float times = 1.0F;
            if (!StringUtils.isEmpty(user.getVipLevel())) {
                VipPrivilegeLevelBO vipPrivilegeLevelBOPar = new VipPrivilegeLevelBO();
                vipPrivilegeLevelBOPar.setLevelId(user.getVipLevel());
                vipPrivilegeLevelBOPar.setPrivilegeId("A_YHDJSJ");
                VipPrivilegeLevelBO vipPrivilegeLevelBO = vipPrivilegeLevelService.selectLevelIdPrivilegeId(vipPrivilegeLevelBOPar);
                if (vipPrivilegeLevelBO != null && vipPrivilegeLevelBO.getStatus()) {
                    if (!StringUtils.isEmpty(vipPrivilegeLevelBO.getVal1())) {
                        times = Float.parseFloat(vipPrivilegeLevelBO.getVal1());
                    }
                }
            }

            PointsLogBO pointsLogBO = new PointsLogBO();
            pointsLogBO.setUserId(userId);
            pointsLogBO.setIncome((int) (pointsRuleBO.getPoints() * times));
            pointsLogBO.setOutgo(0);
            pointsLogBO.setRuleId(pointsRuleBO.getId());
            pointsLogBO.setRemark("用户等级提升奖励");
            pointsLogService.insert(pointsLogBO);

            try {
                userFeedbackMsgService.expLevelUp(userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<ExperienceLogQueryBO> selectList(Map map) {
        return experienceLogRoMapper.selectList(map);
    }

    @Override
    public List<ExpLogUcBO> selectListByUser(Map<String, Object> map) {
        return experienceLogRoMapper.selectListByUser(map);
    }

    @Override
    public float selectCount(Map<String, Object> map) {
        return experienceLogRoMapper.selectCount(map);
    }
}
