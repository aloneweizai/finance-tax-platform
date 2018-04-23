package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.PointsRuleMapper;
import com.abc12366.uc.mapper.db2.PointsRuleRoMapper;
import com.abc12366.uc.model.PointsRule;
import com.abc12366.uc.model.bo.PointsRuleBO;
import com.abc12366.uc.model.bo.PointsRuleInsertBO;
import com.abc12366.uc.model.bo.PointsRuleUpdateBO;
import com.abc12366.uc.service.ExperienceRuleService;
import com.abc12366.uc.service.PointsRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 9:13
 */
@Service
public class PointsRuleServiceImpl implements PointsRuleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PointsLogServiceImpl.class);

    @Autowired
    private PointsRuleMapper uPointRuleMapper;

    @Autowired
    private PointsRuleRoMapper uPointRuleRoMapper;

    @Autowired
    private ExperienceRuleService experienceRuleService;

    @Override
    public List<PointsRuleBO> selectList(Map map) {
        return uPointRuleRoMapper.selectList(map);
    }

    @Override
    public PointsRuleBO selectOne(String id) {
        PointsRuleBO pointsRuleBO = uPointRuleRoMapper.selectOne(id);
        if (pointsRuleBO == null) {
            LOGGER.warn("查询失败，参数：null");
            throw new ServiceException(4104);
        }
        return pointsRuleBO;
    }

    @Transactional("db1TxManager")
    @Override
    public PointsRuleBO insert(PointsRuleInsertBO pointsRuleInsertBO) {
        if (pointsRuleInsertBO == null) {
            LOGGER.warn("新增失败，参数：{}" + null);
            throw new ServiceException(4101);
        }
        //积分规则的规则名称和规则代码唯一性校验
        List<PointsRuleBO> pointsRuleBOList = uPointRuleRoMapper.selectList(null);
        for (PointsRuleBO pointsRuleBO : pointsRuleBOList) {
            if (pointsRuleBO.getName().equals(pointsRuleInsertBO.getName())) {
                LOGGER.warn("新增失败，参数：{}", pointsRuleInsertBO);
                throw new ServiceException(4608);
            }
            if (pointsRuleBO.getCode().equals(pointsRuleInsertBO.getCode())) {
                LOGGER.warn("新增失败，参数：{}", pointsRuleInsertBO);
                throw new ServiceException(4609);
            }
        }

        PointsRule pointsRule = new PointsRule();
        BeanUtils.copyProperties(pointsRuleInsertBO, pointsRule);
        Date date = new Date();
        pointsRule.setId(Utils.uuid());
        pointsRule.setCreateTime(date);
        pointsRule.setLastUpdate(date);
        int result = uPointRuleMapper.insert(pointsRule);
        if (result < 1) {
            LOGGER.warn("新增失败，参数：{}" + pointsRuleInsertBO);
            throw new ServiceException(4101);
        }
        PointsRuleBO pointsRuleBO1 = new PointsRuleBO();
        BeanUtils.copyProperties(pointsRule, pointsRuleBO1);
        return pointsRuleBO1;
    }

    @Transactional("db1TxManager")
    @Override
    public PointsRuleBO update(PointsRuleUpdateBO pointsRuleUpdateBO, String id) {
        if (pointsRuleUpdateBO == null) {
            LOGGER.warn("更新失败，参数：{}：{}", id);
            throw new ServiceException(4102);
        }
        //修改停用经验值规则之前做校验：是否有关联的任务在使用此条规则，若有，则不允许修改此条规则
        experienceRuleService.isValidSysTaskRelatedTheRule(id);

        //积分规则的规则名称和规则代码唯一性校验
        List<PointsRuleBO> pointsRuleBOList = uPointRuleRoMapper.selectList(null);
        //这条数据本身不做校验
        for (int i = 0; i < pointsRuleBOList.size(); i++) {
            if ((pointsRuleBOList.get(i)).getId().equals(id)) {
                pointsRuleBOList.remove(i);
            }
        }
        for (PointsRuleBO pointsRuleBO : pointsRuleBOList) {
            if (pointsRuleBO.getName().equals(pointsRuleUpdateBO.getName())) {
                LOGGER.warn("新增失败，参数：{}", pointsRuleUpdateBO);
                throw new ServiceException(4608);
            }
            if (pointsRuleBO.getCode().equals(pointsRuleUpdateBO.getCode())) {
                LOGGER.warn("新增失败，参数：{}", pointsRuleUpdateBO);
                throw new ServiceException(4609);
            }
        }

        PointsRuleBO uPointsRuleQuery = uPointRuleRoMapper.selectOne(id);
        if (uPointsRuleQuery == null) {
            LOGGER.warn("更新失败，参数：{}：{}", pointsRuleUpdateBO.toString(), id);
            throw new ServiceException(4102);
        }

        PointsRule pointsRule = new PointsRule();
        BeanUtils.copyProperties(pointsRuleUpdateBO, pointsRule);
        pointsRule.setId(id);
        pointsRule.setCreateTime(null);
        pointsRule.setLastUpdate(new Date());
        int result = uPointRuleMapper.update(pointsRule);
        if (result != 1) {
            LOGGER.warn("更新失败，参数：{}：{}" + pointsRuleUpdateBO.toString(), id);
            throw new ServiceException(4102);
        }
        PointsRuleBO pointsRuleBOReturn = new PointsRuleBO();
        BeanUtils.copyProperties(pointsRule, pointsRuleBOReturn);
        return pointsRuleBOReturn;
    }

    @Override
    public int delete(String id) {
        LOGGER.info("{}", id);

        //修改停用经验值规则之前做校验：是否有关联的任务在使用此条规则，若有，则不允许修改此条规则
        experienceRuleService.isValidSysTaskRelatedTheRule(id);

        int result = uPointRuleMapper.delete(id);
        if (result != 1) {
            LOGGER.warn("删除失败，参数为：id=" + id);
            throw new ServiceException(4103);
        }
        return 1;
    }

    @Override
    public PointsRuleBO selectValidOne(String id) {
        return uPointRuleRoMapper.selectValidOne(id);
    }

    @Override
    public void enableOrDisable(String id, String status) {
        LOGGER.info("{}:{}", id, status);
        if ((!status.equals("true")) && (!status.equals("false"))) {
            throw new ServiceException(4614);
        }
        if(status.equals("false")){
            //修改停用经验值规则之前做校验：是否有关联的任务在使用此条规则，若有，则不允许修改此条规则
            experienceRuleService.isValidSysTaskRelatedTheRule(id);
        }

        boolean modifyStatus = status.equals("true");
        PointsRule pointsRule = new PointsRule();
        pointsRule.setId(id);
        pointsRule.setStatus(modifyStatus);
        pointsRule.setLastUpdate(new Date());
        int result = uPointRuleMapper.enableOrDisable(pointsRule);
        if (result < 1) {
            if (modifyStatus) {
                throw new ServiceException(4621);
            }
            throw new ServiceException(4622);
        }
    }

    @Override
    public PointsRuleBO selectValidOneByCode(String ruleCode) {
        LOGGER.info("根据编码查询积分规则，编码：{}", ruleCode);
        return uPointRuleRoMapper.selectValidOneByCode(ruleCode);
    }
}
