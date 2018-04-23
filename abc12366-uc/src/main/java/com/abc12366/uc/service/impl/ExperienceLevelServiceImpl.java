package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.ExperienceLevelMapper;
import com.abc12366.uc.mapper.db2.ExperienceLevelRoMapper;
import com.abc12366.uc.model.ExperienceLevel;
import com.abc12366.uc.model.bo.ExperienceLevelBO;
import com.abc12366.uc.model.bo.ExperienceLevelInsertAndUpdateBO;
import com.abc12366.uc.service.ExperienceLevelService;
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
 * Time: 15:50
 */
@Service
public class ExperienceLevelServiceImpl implements ExperienceLevelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExperienceLevelServiceImpl.class);

    @Autowired
    private ExperienceLevelRoMapper experienceLevelRoMapper;

    @Autowired
    private ExperienceLevelMapper experienceLevelMapper;

    @Override
    public List<ExperienceLevelBO> selectList(Map<String, Object> map) {
        return experienceLevelRoMapper.selectList(map);
    }

    @Override
    public ExperienceLevelBO selectOne(String id) {
        return experienceLevelRoMapper.selectOne(id);
    }

    @Override
    public ExperienceLevelBO selectOne(int exp) {
        return experienceLevelRoMapper.selectOneByExp(exp);
    }

    @Override
    public ExperienceLevelBO insert(ExperienceLevelInsertAndUpdateBO experienceLevelInsertBO) {
        if (experienceLevelInsertBO == null) {
            LOGGER.warn("新增失败,入参为：" + null);
            throw new ServiceException(4101);
        }

        //等级名称，经验值范围唯一性校验
        List<ExperienceLevelBO> experienceLevelBOList = experienceLevelRoMapper.selectList(null);
        for (ExperienceLevelBO experienceLevelBO : experienceLevelBOList) {
            if (experienceLevelBO.getName().equals(experienceLevelInsertBO.getName())) {
                LOGGER.warn("新增失败，参数：{}", experienceLevelInsertBO);
                throw new ServiceException(4610);
            }
            //判断经验值范围与已有经验值等级是否存在冲突
            if (((experienceLevelInsertBO.getMinValue() >= experienceLevelBO.getMinValue()) &&
                    (experienceLevelInsertBO.getMinValue() <= experienceLevelBO.getMaxValue()))
                    || ((experienceLevelInsertBO.getMaxValue() >= experienceLevelBO.getMinValue()) &&
                    (experienceLevelInsertBO.getMaxValue() <= experienceLevelBO.getMaxValue()))
                    || ((experienceLevelBO.getMinValue() >= experienceLevelInsertBO.getMinValue()) &&
                    (experienceLevelBO.getMinValue() <= experienceLevelInsertBO.getMaxValue()))
                    || ((experienceLevelBO.getMaxValue() >= experienceLevelInsertBO.getMinValue()) &&
                    (experienceLevelBO.getMaxValue() <= experienceLevelInsertBO.getMaxValue()))) {
                LOGGER.warn("新增失败，参数：{}", experienceLevelInsertBO);
                throw new ServiceException(4611);
            }
        }

        ExperienceLevel experienceLevel = new ExperienceLevel();
        BeanUtils.copyProperties(experienceLevelInsertBO, experienceLevel);
        Date date = new Date();
        experienceLevel.setId(Utils.uuid());
        experienceLevel.setCreateTime(date);
        experienceLevel.setLastUpdate(date);
        if (StringUtils.isEmpty(experienceLevelInsertBO.getStatus())) {
            experienceLevel.setStatus(true);
        }
        int result = experienceLevelMapper.insert(experienceLevel);
        if (result != 1) {
            LOGGER.warn("新增失败,入参为：" + null);
            throw new ServiceException(4101);
        }
        ExperienceLevelBO experienceLevelReturn = new ExperienceLevelBO();
        BeanUtils.copyProperties(experienceLevel, experienceLevelReturn);
        return experienceLevelReturn;
    }

    @Override
    public ExperienceLevelBO update(ExperienceLevelInsertAndUpdateBO experienceLevelUpdateBO, String id) {
        if (experienceLevelUpdateBO == null) {
            LOGGER.warn("修改失败,入参为：" + null);
            throw new ServiceException(4102);
        }

        //等级名称，经验值范围唯一性校验
        List<ExperienceLevelBO> experienceLevelBOList = experienceLevelRoMapper.selectList(null);
        //这条数据本身不做校验
        for (int i = 0; i < experienceLevelBOList.size(); i++) {
            if (experienceLevelBOList.get(i).getId().equals(id)) {
                experienceLevelBOList.remove(i);
            }
        }
        for (ExperienceLevelBO experienceLevelBO : experienceLevelBOList) {
            if (experienceLevelBO.getName().equals(experienceLevelUpdateBO.getName())) {
                LOGGER.warn("修改失败，参数：{}", experienceLevelUpdateBO);
                throw new ServiceException(4610);
            }
            //判断经验值范围与已有经验值等级是否存在冲突
            if (((experienceLevelUpdateBO.getMinValue() >= experienceLevelBO.getMinValue()) &&
                    (experienceLevelUpdateBO.getMinValue() <= experienceLevelBO.getMaxValue()))
                    || ((experienceLevelUpdateBO.getMaxValue() >= experienceLevelBO.getMinValue()) &&
                    (experienceLevelUpdateBO.getMaxValue() <= experienceLevelBO.getMaxValue()))
                    || ((experienceLevelBO.getMinValue() >= experienceLevelUpdateBO.getMinValue()) &&
                    (experienceLevelBO.getMinValue() <= experienceLevelUpdateBO.getMaxValue()))
                    || ((experienceLevelBO.getMaxValue() >= experienceLevelUpdateBO.getMinValue()) &&
                    (experienceLevelBO.getMaxValue() <= experienceLevelUpdateBO.getMaxValue()))) {
                LOGGER.warn("修改失败，参数：{}", experienceLevelUpdateBO);
                throw new ServiceException(4611);
            }
        }

        ExperienceLevel experienceLevel = new ExperienceLevel();
        BeanUtils.copyProperties(experienceLevelUpdateBO, experienceLevel);
        experienceLevel.setId(id);
        experienceLevel.setLastUpdate(new Date());
        if (StringUtils.isEmpty(experienceLevelUpdateBO.getStatus())) {
            experienceLevel.setStatus(null);
        }
        int result = experienceLevelMapper.update(experienceLevel);
        if (result != 1) {
            LOGGER.warn("修改失败,入参为：" + null);
            throw new ServiceException(4102);
        }

        ExperienceLevelBO experienceLevelReturn = new ExperienceLevelBO();
        BeanUtils.copyProperties(experienceLevel, experienceLevelReturn);
        return experienceLevelReturn;
    }

    @Override
    public int delete(String id) {
        int result = experienceLevelMapper.delete(id);
        if (result < 1) {
            LOGGER.warn("删除失败！");
            throw new ServiceException(4103);
        }
        return 1;
    }

    @Override
    public void enableOrDisable(String id, String status) {
        LOGGER.info("{}:{}", id, status);
        if ((!status.equals("true")) && (!status.equals("false"))) {
            throw new ServiceException(4614);
        }
        boolean modifyStatus = status.equals("true");
        ExperienceLevel experienceLevel = new ExperienceLevel();
        experienceLevel.setId(id);
        experienceLevel.setStatus(modifyStatus);
        experienceLevel.setLastUpdate(new Date());
        int result = experienceLevelMapper.enableOrDisable(experienceLevel);
        if (result < 1) {
            if (modifyStatus) {
                throw new ServiceException(4619);
            }
            throw new ServiceException(4620);
        }
    }
}
