package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.ExperienceRuleMapper;
import com.abc12366.uc.mapper.db2.ExperienceRuleRoMapper;
import com.abc12366.uc.mapper.db2.SysTaskRoMapper;
import com.abc12366.uc.model.ExperienceRule;
import com.abc12366.uc.model.SysTask;
import com.abc12366.uc.model.bo.ExperienceRuleBO;
import com.abc12366.uc.model.bo.ExperienceRuleInsertBO;
import com.abc12366.uc.model.bo.ExperienceRuleUpdateBO;
import com.abc12366.uc.service.ExperienceRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 11:24
 */
@Service
public class ExperienceRuleServiceImpl implements ExperienceRuleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExperienceRuleServiceImpl.class);

    @Autowired
    private ExperienceRuleRoMapper experienceRuleRoMapper;

    @Autowired
    private ExperienceRuleMapper experienceRuleMapper;

    @Autowired
    private SysTaskRoMapper sysTaskRoMapper;

    @Override
    public List<ExperienceRuleBO> selectList(Map<String, Object> map) {
        LOGGER.info("{}", map);
        return experienceRuleRoMapper.selectList(map);
    }

    @Override
    public ExperienceRuleBO selectOne(String id) {
        LOGGER.info("{}", id);
        return experienceRuleRoMapper.selectOne(id);
    }

    @Override
    public ExperienceRuleBO insert(ExperienceRuleInsertBO experienceRuleInsertBO) {
        if (experienceRuleInsertBO == null) {
            LOGGER.warn("新增失败，参数：" + null);
            throw new ServiceException(4101);
        }
        //经验值规则新增，规则名称、规则代码唯一性校验
        List<ExperienceRuleBO> experienceRuleBOList = experienceRuleRoMapper.selectList(null);
        for (ExperienceRuleBO experieneRuleBO : experienceRuleBOList) {
            if (experieneRuleBO.getName().equals(experienceRuleInsertBO.getName())) {
                LOGGER.warn("新增失败，参数：{}", experienceRuleInsertBO);
                throw new ServiceException(4602);
            }
            if (experieneRuleBO.getCode().equals(experienceRuleInsertBO.getCode())) {
                LOGGER.warn("新增失败，参数：{}", experienceRuleInsertBO);
                throw new ServiceException(4603);
            }
        }

        ExperienceRule experienceRule = new ExperienceRule();
        Date date = new Date();
        experienceRule.setId(Utils.uuid());
        experienceRule.setCreateTime(date);
        experienceRule.setLastUpdate(date);
        BeanUtils.copyProperties(experienceRuleInsertBO, experienceRule);
        int result = experienceRuleMapper.insert(experienceRule);
        if (result != 1) {
            LOGGER.warn("新增失败，参数：" + experienceRule);
            throw new ServiceException(4101);
        }
        ExperienceRuleBO experienceRuleReturn = new ExperienceRuleBO();
        BeanUtils.copyProperties(experienceRule, experienceRuleReturn);
        return experienceRuleReturn;
    }

    @Override
    public ExperienceRuleBO update(ExperienceRuleUpdateBO experienceRuleUpdateBO, String id) {
        ExperienceRuleBO experienceRuleQueryBO = experienceRuleRoMapper.selectOne(id);
        if (experienceRuleQueryBO == null) {
            LOGGER.warn("修改失败，没有可被修改的数据，参数：" + id);
            throw new ServiceException(4102);
        }

        //修改停用经验值规则之前做校验：是否有关联的任务在使用此条规则，若有，则不允许修改此条规则
        isValidSysTaskRelatedTheRule(id);

        //经验值规则新增，规则名称、规则代码唯一性校验
        List<ExperienceRuleBO> experienceRuleBOList = experienceRuleRoMapper.selectList(null);
        //本身不计入校验数据
        for (int i = 0; i < experienceRuleBOList.size(); i++) {
            if ((experienceRuleBOList.get(i)).getId().equals(id)) {
                experienceRuleBOList.remove(i);
            }
        }
        if (experienceRuleUpdateBO.getName() != null) {
            for (ExperienceRuleBO experieneRuleBO : experienceRuleBOList) {
                if (experieneRuleBO.getName().equals(experienceRuleUpdateBO.getName())) {
                    LOGGER.warn("修改失败，参数：{}", experienceRuleUpdateBO);
                    throw new ServiceException(4602);
                }
            }
        }
        if (experienceRuleUpdateBO.getCode() != null) {
            for (ExperienceRuleBO experieneRuleBO : experienceRuleBOList) {
                if (experieneRuleBO.getCode().equals(experienceRuleUpdateBO.getCode())) {
                    LOGGER.warn("修改失败，参数：{}", experienceRuleUpdateBO);
                    throw new ServiceException(4603);
                }
            }
        }

        ExperienceRule experienceRule = new ExperienceRule();
        BeanUtils.copyProperties(experienceRuleUpdateBO, experienceRule);
        experienceRule.setId(id);
        experienceRule.setLastUpdate(new Date());
        if (StringUtils.isEmpty(experienceRuleUpdateBO.getStatus())) {
            experienceRule.setStatus(null);
        }
        int result = experienceRuleMapper.update(experienceRule);
        if (result != 1) {
            LOGGER.warn("修改失败，参数：" + experienceRule);
            throw new ServiceException(4102);
        }
        ExperienceRuleBO experienceRuleReturn = new ExperienceRuleBO();
        BeanUtils.copyProperties(experienceRule, experienceRuleReturn);
        return experienceRuleReturn;
    }

    @Override
    public int delete(String id) {
        LOGGER.info("{}", id);
        //修改停用经验值规则之前做校验：是否有关联的任务在使用此条规则，若有，则不允许修改此条规则
        isValidSysTaskRelatedTheRule(id);

        int result = experienceRuleMapper.delete(id);
        if (result != 1) {
            LOGGER.warn("删除失败，参数为：id=" + id);
            throw new ServiceException(4103);
        }
        return 1;
    }

    @Override
    public ExperienceRuleBO selectValidOne(String ruleId) {
        LOGGER.info("{}", ruleId);
        return experienceRuleRoMapper.selectValidOne(ruleId);
    }

    @Override
    public void enableOrDisable(String id, String status) {
        LOGGER.info("{}:{}", id, status);
        if ((!status.equals("true")) && (!status.equals("false"))) {
            throw new ServiceException(4614);
        }

        if (status.equals("false")) {
            //修改停用经验值规则之前做校验：是否有关联的任务在使用此条规则，若有，则不允许修改此条规则
            isValidSysTaskRelatedTheRule(id);
        }

        boolean modifyStatus = status.equals("true");
        ExperienceRule experienceRule = new ExperienceRule();
        experienceRule.setId(id);
        experienceRule.setStatus(modifyStatus);
        experienceRule.setLastUpdate(new Date());
        int result = experienceRuleMapper.enableOrDisable(experienceRule);
        if (result < 1) {
            if (modifyStatus) {
                throw new ServiceException(4615);
            }
            throw new ServiceException(4616);
        }
    }

    @Override
    public void isValidSysTaskRelatedTheRule(String ruleId) {
        List<SysTask> sysTasks = sysTaskRoMapper.selectValidSysTaskByRuleId(ruleId);
        if (sysTasks != null && sysTasks.size() > 0) {
            throw new ServiceException(4713);
        }
    }

    @Override
    public ExperienceRuleBO selectValidOneByCode(String ruleCode) {
        LOGGER.info("查询经验值规则，规则编码：{}", ruleCode);
        return experienceRuleRoMapper.selectValidOneByCode(ruleCode);
    }
}
