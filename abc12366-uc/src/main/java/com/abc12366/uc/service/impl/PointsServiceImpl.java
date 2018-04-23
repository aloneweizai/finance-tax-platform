package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.*;
import com.abc12366.uc.mapper.db1.PointMapper;
import com.abc12366.uc.mapper.db1.UserMapper;
import com.abc12366.uc.mapper.db2.PointsRoMapper;
import com.abc12366.uc.model.Message;
import com.abc12366.uc.model.User;
import com.abc12366.uc.model.bo.*;
import com.abc12366.uc.service.MessageService;
import com.abc12366.uc.service.PointsLogService;
import com.abc12366.uc.service.PointsRuleService;
import com.abc12366.uc.service.PointsService;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-20
 * Time: 21:43
 */
@Service
public class PointsServiceImpl implements PointsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PointsServiceImpl.class);

    @Autowired
    private PointsRoMapper pointsRoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PointsLogService pointsLogService;

    @Autowired
    private PointMapper pointMapper;

    @Autowired
    private PointsRuleService pointsRuleService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public PointsBO selectOne(String userId) {
        PointsBO pointsBO = null;
        if (redisTemplate.hasKey(userId + "_Points")) {
            pointsBO = JSONObject.parseObject(redisTemplate.opsForValue()
                    .get(userId + "_Points"), PointsBO.class);
        } else {
            pointsBO = pointsRoMapper.selectOne(userId);
            if (pointsBO != null) {
                redisTemplate.opsForValue().set(userId + "_Points", JSONObject.toJSONString(pointsBO), RedisConstant
                        .DAY_1, TimeUnit.DAYS);
            }
        }
        return pointsBO;
    }

    @Override
    public void compute(PointComputeBO pointComputeBO) {
        //查询出对应的积分规则
        List<PointCodex> pointCodexList = pointsRoMapper.selectCodexList(pointComputeBO);
        if (pointCodexList == null || pointCodexList.size() < 1) {
            throw new ServiceException("0000", "没有对应的积分规则");
        }
        PointCodex pointCodex = pointCodexList.get(0);
        if (pointCodex.getUpoint() == null || "".equals(pointCodex.getUpoint().toString()) || pointCodex.getPeriod()
                == null || "".equals(pointCodex.getPeriod().trim())) {
            throw new ServiceException(4855);
        }

        //查看获取经验值次数是否允许范围内
        String period = pointCodex.getPeriod().toUpperCase();
        if ("".equals(period.trim()) || (!"D".equals(period) && !"M".equals(period) && !"Y".equals(period) && !"A"
                .equals(period))) {
            return;
        }
        if (!period.trim().equals("A")) {
            Date startTime = new Date();
            Date endTime = new Date();
            if (!period.trim().equals("") && (period.equals("D") || period.equals("M") || period.equals("Y"))) {
                switch (pointCodex.getPeriod().toUpperCase()) {
                    case "D":
                        startTime = DateUtils.getFirstHourOfDay();
                        endTime = DateUtils.getFirstHourOfLastDay();
                        break;
                    case "M":
                        startTime = DateUtils.getFirstDayOfMonth();
                        endTime = DateUtils.getFirstDayOfLastMonth();
                        break;
                    case "Y":
                        startTime = DateUtils.getFirstMonthOfYear();
                        endTime = DateUtils.getFirstMonthOfLastYear();
                        break;
                }

                PointComputeLogParam param = new PointComputeLogParam();
                param.setUserId(pointComputeBO.getUserId());
                param.setTimeType(pointCodex.getPeriod().toUpperCase());
                param.setUpointCodexId(pointCodex.getId());
                param.setStartTime(startTime);
                param.setEndTime(endTime);
                List<PointComputeLog> computeLogList = pointsRoMapper.selectPointComputeLog(param);
                if (computeLogList != null && computeLogList.size() >= pointCodex.getDegree()) {
                    return;
                }
            }
        }
        //根据规则计算用户积分值变化
        User user = userMapper.selectOne(pointComputeBO.getUserId());
        if (user == null) {
            throw new ServiceException(4018);
        }

        PointsLogBO pointsLog = new PointsLogBO();
        pointsLog.setUserId(pointComputeBO.getUserId());
        pointsLog.setRuleId(pointCodex.getUpointruleCode());
        pointsLog.setRemark(pointCodex.getRemark());
        if (pointCodex.getUpoint() < 0) {
            pointsLog.setIncome(0);
            pointsLog.setOutgo(-pointCodex.getUpoint());
        } else {
            pointsLog.setIncome(pointCodex.getUpoint());
            pointsLog.setOutgo(0);
        }
        pointsLogService.insert(pointsLog);

        //记录用户特定行为升级用户等级日志
        PointComputeLog pointComputeLog = new PointComputeLog();
        pointComputeLog.setId(Utils.uuid());
        pointComputeLog.setUserId(pointComputeBO.getUserId());
        pointComputeLog.setUpointCodexId(pointCodex.getId());
        pointComputeLog.setTimeType(pointCodex.getPeriod().toUpperCase());
        pointComputeLog.setCreateTime(new Date());
        pointMapper.insertComputeLog(pointComputeLog);
    }

    @Override
    public List<PointCodex> codex(String upointCodexId, List<PointCodex> codexList) {
        LOGGER.info("{}:{}", upointCodexId, codexList);
        //先批量删除
        pointMapper.deleteByRuleCode(upointCodexId);

        //再批量新增
        List<PointCodex> pointCodexList = new ArrayList<>();
        for (PointCodex aCodexList : codexList) {
            PointCodex codex = new PointCodex();
            BeanUtils.copyProperties(aCodexList, codex);
            codex.setId(Utils.uuid());
            pointMapper.insert(codex);
            pointCodexList.add(codex);
        }
        return pointCodexList;
    }

    @Override
    public void deleteCodex(String id) {
        pointMapper.deleteCodex(id);
    }

    @Override
    public List<PointCodex> codexList() {
        return pointsRoMapper.codexList();
    }

    @Override
    public PointCodex selectCodexByRuleCode(String ruleCode) {
        LOGGER.info("{}", ruleCode);
        return pointsRoMapper.selectCodexByRuleCode(ruleCode);
    }

    @Override
    public int calculate(PointCalculateBO pointCalculateBO) {
        //查询出对应的积分规则
        PointsRuleBO pointsRuleBO = pointsRuleService.selectValidOneByCode(pointCalculateBO.getRuleCode());
        if (pointsRuleBO == null) {
            return 0;
        }

        //查看获取经验值次数是否允许范围内
        String period = pointsRuleBO.getPeriod().toUpperCase();
        if (!"D".equals(period) && !"M".equals(period) && !"Y".equals(period) && !"A".equals(period)) {
            return 0;
        }
        if (!"A".equals(period.trim())) {
            Date startTime = new Date();
            Date endTime = new Date();
            if (!"".equals(period.trim()) && ("D".equals(period) || "M".equals(period) || "Y".equals(period))) {
                switch (period) {
                    case "D":
                        startTime = DateUtils.getFirstHourOfDay();
                        endTime = DateUtils.getFirstHourOfLastDay();
                        break;
                    case "M":
                        startTime = DateUtils.getFirstDayOfMonth();
                        endTime = DateUtils.getFirstDayOfLastMonth();
                        break;
                    case "Y":
                        startTime = DateUtils.getFirstMonthOfYear();
                        endTime = DateUtils.getFirstMonthOfLastYear();
                        break;
                }

                PointComputeLogParam param = new PointComputeLogParam();
                param.setUserId(pointCalculateBO.getUserId());
                param.setTimeType(period);
                param.setStartTime(startTime);
                param.setEndTime(endTime);
                param.setRuleId(pointsRuleBO.getId());
                List<PointComputeLog> computeLogList = pointsRoMapper.selectCalculateLog(param);
                if (computeLogList != null && computeLogList.size() >= pointsRuleBO.getDegree()) {
                    return 0;
                }
            }
        }
        //根据规则计算用户积分值变化
        User user = userMapper.selectValidOne(pointCalculateBO.getUserId());
        if (user == null) {
            throw new ServiceException(4018);
        }
        
        
        //2018-02-28
        if(pointCalculateBO.getPoints()!=null && pointCalculateBO.getPoints()!= 0){
        	pointsRuleBO.setPoints(pointCalculateBO.getPoints());
        	if(pointCalculateBO.getPoints() < 0 && -pointCalculateBO.getPoints() > user.getPoints()){//扣除积分的时候积分不足情况
        		throw new ServiceException(4635);
        	}
        }
        if(StringUtils.isNotEmpty(pointCalculateBO.getRemark())){
        	pointsRuleBO.setDescription(pointCalculateBO.getRemark());
        }
        
        PointsLogBO pointsLog = new PointsLogBO();
        pointsLog.setUserId(pointCalculateBO.getUserId());
        pointsLog.setRuleId(pointsRuleBO.getId());
        pointsLog.setRemark(pointsRuleBO.getDescription());
        if (pointsRuleBO.getPoints() < 0) {
            pointsLog.setIncome(0);
            pointsLog.setOutgo(-pointsRuleBO.getPoints());
        } else {
            pointsLog.setIncome(pointsRuleBO.getPoints());
            pointsLog.setOutgo(0);
        }
        pointsLogService.insert(pointsLog);

        //记录用户特定行为升级用户等级日志
        PointComputeLog pointComputeLog = new PointComputeLog();
        pointComputeLog.setId(Utils.uuid());
        pointComputeLog.setUserId(pointCalculateBO.getUserId());
        pointComputeLog.setTimeType(period);
        pointComputeLog.setCreateTime(new Date());
        pointComputeLog.setRuleId(pointsRuleBO.getId());
        pointMapper.insertComputeLog(pointComputeLog);

        

        return pointsRuleBO.getPoints();
    }

    @Override
    public void batchAward(PointBatchAwardBO pointBatchAwardBO) {
        //如果积分规则为空则返回
        PointsRuleBO pointsRuleBO = pointsRuleService.selectValidOneByCode(TaskConstant
                .POINT_RULE_BANGBANG_BATCH_AWARD_CODE);
        if (pointsRuleBO == null) {
            return;
        }
        String ruleId = pointsRuleBO.getId();
        for (PointAwardBO pointAwardBO : pointBatchAwardBO.getPointAwardBOList()) {
            PointsLogBO pointsLog = new PointsLogBO();
            pointsLog.setUserId(pointAwardBO.getUserId());
            pointsLog.setRuleId(ruleId);
            pointsLog.setRemark(pointsRuleBO.getDescription());
            if (pointAwardBO.getPoint() < 0) {
                pointsLog.setIncome(0);
                pointsLog.setOutgo(pointAwardBO.getPoint());
            } else {
                pointsLog.setIncome(pointAwardBO.getPoint());
                pointsLog.setOutgo(0);
            }
            pointsLogService.insert(pointsLog);

            //向用户发送帮帮消息
            sendBangbangMsg(pointAwardBO);
        }
    }

    private void sendBangbangMsg(PointAwardBO pointAwardBO) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Message message = new Message();
        message.setUserId(pointAwardBO.getUserId());
        message.setType(MessageConstant.BB_MESSAGE);
        message.setBusiType(MessageConstant.BUSI_TYPE_BANGBANG);
        message.setBusinessId(pointAwardBO.getUserId());
        message.setStatus("1");
        message.setUrl("");
        message.setContent("您在帮邦获得积分奖励：" + pointAwardBO.getPoint());
        try {
            messageService.insert(message, request);
        } catch (Exception e) {
            throw new ServiceException(4822);
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    @Transactional("db1TxManager")
    public ResponseEntity integralMultiplication(UserBO sendUser,
                                                 UserBO reciveUser, PointsRuleBO bo) {

        PointsLogBO spointsLog = new PointsLogBO();
        spointsLog.setUserId(sendUser.getId());
        spointsLog.setRuleId(bo.getId());
        spointsLog.setRemark("会员特权积分转让给用户：" + reciveUser.getUsername());
        spointsLog.setIncome(0);
        spointsLog.setOutgo(bo.getPoints());
        pointsLogService.insert(spointsLog);

        PointsLogBO rpointsLog = new PointsLogBO();
        rpointsLog.setUserId(reciveUser.getId());
        rpointsLog.setRuleId(bo.getId());
        rpointsLog.setRemark("会员特权积分转让，来自会员用户：" + sendUser.getUsername());
        rpointsLog.setIncome(bo.getPoints());
        rpointsLog.setOutgo(0);
        pointsLogService.insert(rpointsLog);

        return ResponseEntity.ok(Utils.kv());
    }
}