package com.abc12366.uc.service.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.TaskConstant;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.SysTaskMapper;
import com.abc12366.uc.mapper.db2.SysTaskRoMapper;
import com.abc12366.uc.model.SysTask;
import com.abc12366.uc.model.bo.SysTaskBO;
import com.abc12366.uc.model.bo.SysTaskInsertAndUpdateBO;
import com.abc12366.uc.model.bo.SysTaskListBO;
import com.abc12366.uc.service.SysTaskService;
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
 * Date: 2017-05-24
 * Time: 9:51
 */
@Service
public class SysTaskServiceImpl implements SysTaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysTaskServiceImpl.class);

    @Autowired
    private SysTaskMapper sysTaskMapper;

    @Autowired
    private SysTaskRoMapper sysTaskRoMapper;

    @Override
    public List<SysTaskBO> selectList(Map<String, String> map) {
        return sysTaskRoMapper.selectList(map);
    }

    @Override
    public List<SysTaskBO> selectListByDateType(String dateType) {
        return sysTaskRoMapper.selectListByDateType(dateType);
    }

    @Override
    public List<SysTaskBO> selectDeployedList(Map<String, String> map) {
        return sysTaskRoMapper.selectDeployedList(map);
    }

    @Override
    public SysTaskBO selectOne(String id) {
        return sysTaskRoMapper.selectOne(id);
    }

    @Override
    public SysTaskBO insert(SysTaskInsertAndUpdateBO sysTaskInsertBO) {
        if (sysTaskInsertBO == null) {
            LOGGER.warn("新增失败，参数为：" + null);
            throw new ServiceException(4101);
        }
        //系统任务编码唯一性校验
        List<SysTaskBO> codeList = sysTaskRoMapper.selectListByCode(sysTaskInsertBO.getCode());
        if (codeList != null && codeList.size() > 0) {
            throw new ServiceException(4714);
        }

        //任务名称唯一性校验逻辑
        List<SysTaskBO> sysTaskBOList = sysTaskRoMapper.selectList(null);
        for (SysTaskBO sysTaskBO : sysTaskBOList) {
            if (sysTaskBO.getName().equals(sysTaskInsertBO.getName())) {
                LOGGER.info("新增失败，参数为：{}", sysTaskInsertBO);
                throw new ServiceException(4601);
            }
        }

        //如果是特殊任务，则必须有开始和结束时间
        if (sysTaskInsertBO.getType() != null && sysTaskInsertBO.getType().equals(TaskConstant.SPECIAL_TASK_TYPE)) {
            if (sysTaskInsertBO.getStartTime() == null || sysTaskInsertBO.getEndTime() == null) {
                throw new ServiceException(4041);
            }
        }

        SysTask sysTask = new SysTask();
        BeanUtils.copyProperties(sysTaskInsertBO, sysTask);
        Date date = new Date();
        sysTask.setId(Utils.uuid());
        sysTask.setCreateTime(date);
        sysTask.setLastUpdate(date);
        int result = sysTaskMapper.insert(sysTask);
        if (result != 1) {
            LOGGER.warn("新增失败，参数为：", sysTask);
            throw new ServiceException(4101);
        }
        SysTaskBO sysTaskBO = new SysTaskBO();
        BeanUtils.copyProperties(sysTask, sysTaskBO);
        return sysTaskBO;
    }

    @Override
    public SysTaskBO update(SysTaskInsertAndUpdateBO sysTaskUpdateBO, String id) {
        if (sysTaskUpdateBO == null) {
            LOGGER.warn("修改失败，参数为：" + null);
            throw new ServiceException(4102);
        }
        //系统任务编码唯一性校验
        if(StringUtils.isEmpty(sysTaskUpdateBO.getCode())){
            List<SysTaskBO> codeList = sysTaskRoMapper.selectListByCode(sysTaskUpdateBO.getCode());
            if (codeList != null && codeList.size() > 0) {
                throw new ServiceException(4714);
            }
        }

        //任务名称唯一性校验逻辑
        List<SysTaskBO> sysTaskBOList = sysTaskRoMapper.selectList(null);
        //这条数据本身不做校验
        for (int i = 0; i < sysTaskBOList.size(); i++) {
            if ((sysTaskBOList.get(i)).getId().equals(id)) {
                sysTaskBOList.remove(i);
            }
        }
        if (sysTaskUpdateBO.getName() != null) {
            for (SysTaskBO sysTaskBO : sysTaskBOList) {
                if (sysTaskBO.getName().equals(sysTaskUpdateBO.getName())) {
                    LOGGER.info("参数失败，参数为：{}", sysTaskUpdateBO);
                    throw new ServiceException(4601);
                }
            }
        }

        SysTaskBO sysTaskQuery = sysTaskRoMapper.selectOne(id);
        if (sysTaskQuery == null) {
            LOGGER.warn("修改失败，不存在要被修改的数据，参数为：id=" + id);
            throw new ServiceException(4102);
        }
        //若系统任务已发布则不允许修改，修改前必须先撤销发布
        if (sysTaskQuery.isStatus()) {
            LOGGER.warn("修改失败，该任务已发布，撤销发布后才允许修改，参数为：id=" + id);
            throw new ServiceException(4600);
        }
        //如果是特殊任务，则必须有开始和结束时间
        if (sysTaskUpdateBO.getType() != null && sysTaskUpdateBO.getType().equals(TaskConstant.SPECIAL_TASK_TYPE)) {
            if (sysTaskUpdateBO.getStartTime() == null || sysTaskUpdateBO.getEndTime() == null) {
                throw new ServiceException(4041);
            }
        }

        SysTask sysTask = new SysTask();
        BeanUtils.copyProperties(sysTaskUpdateBO, sysTask);
        sysTask.setId(id);
        sysTask.setLastUpdate(new Date());
        int result = sysTaskMapper.update(sysTask);
        if (result != 1) {
            LOGGER.warn("修改失败，参数为：", sysTask);
            throw new ServiceException(4102);
        }
        SysTaskBO sysTaskBO = new SysTaskBO();
        BeanUtils.copyProperties(sysTask, sysTaskBO);
        return sysTaskBO;
    }

    @Override
    public boolean delete(String id) {
        SysTaskBO sysTaskQuery = sysTaskRoMapper.selectOne(id);
        if (sysTaskQuery == null) {
            LOGGER.warn("删除失败，不存在要被删除的数据，参数为：id=" + id);
            throw new ServiceException(4103);
        }
        int result = sysTaskMapper.delete(id);
        if (result != 1) {
            LOGGER.warn("删除失败，参数为：id=" + id);
            throw new ServiceException(4103);
        }
        return true;
    }

    @Override
    public List<SysTaskBO> selectListByType(String type) {
        return sysTaskRoMapper.selectListByType(type);
    }

   

    @Override
    public List<SysTaskBO> selectValidListByTypeAndDateType(String type, String dateType) {
        return sysTaskRoMapper.selectValidListByTypeAndDateType(type, dateType);
    }

    @Override
    public List<SysTaskBO> selectTimeLimitedListByType(String type) {
        return sysTaskRoMapper.selectTimeLimitedListByType(type);
    }

    @Override
    public SysTaskBO selectValidOneByCode(String taskCode) {
        LOGGER.info("根据编码查询一条系统任务，编码：{}", taskCode);
        return sysTaskRoMapper.selectValidOneByCode(taskCode);
    }
}
