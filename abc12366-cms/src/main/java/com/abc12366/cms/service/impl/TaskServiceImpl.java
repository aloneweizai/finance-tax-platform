package com.abc12366.cms.service.impl;

import com.abc12366.cms.mapper.db1.TaskMapper;
import com.abc12366.cms.mapper.db2.TaskRoMapper;
import com.abc12366.cms.model.Task;
import com.abc12366.cms.model.bo.TaskBo;
import com.abc12366.cms.service.TaskService;
import com.abc12366.gateway.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xieyanmao on 2017/5/8.
 */
@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskRoMapper taskRoMapper;

    @Override
    public List<TaskBo> selectList(Map<String, Object> map) {
        //查询模型列表
        List<TaskBo> taskBoList = taskRoMapper.selectList(map);
        return taskBoList;
    }

    @Override
    public TaskBo save(TaskBo taskBo) {
        TaskBo taskBo1 = new TaskBo();
        taskBo1.setTaskName(taskBo.getTaskName());
        int cnt = taskRoMapper.selectCnt(taskBo1);
        if (cnt > 0) {
            throw new ServiceException(4302);
        }
        //保存模型信息
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Task task = new Task();
        taskBo.setTaskId(uuid);
        try {
            BeanUtils.copyProperties(taskBo, task);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        taskMapper.insert(task);
        return taskBo;
    }

    @Override
    public TaskBo selectTask(String taskId) {
        //查询模型信息
        Task task = taskRoMapper.selectByPrimaryKey(taskId);
        TaskBo taskBo = new TaskBo();
        try {
            BeanUtils.copyProperties(task, taskBo);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        return taskBo;
    }

    @Override
    public TaskBo update(TaskBo taskBo) {
        TaskBo taskBo1 = new TaskBo();
        taskBo1.setTaskName(taskBo.getTaskName());
        taskBo1.setTaskId(taskBo.getTaskId());
        int cnt = taskRoMapper.selectCnt(taskBo1);
        if (cnt > 0) {
            throw new ServiceException(5000);
        }
        //更新模型信息
        Task task = new Task();
        try {
            BeanUtils.copyProperties(taskBo, task);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new RuntimeException("类型转换异常：{}", e);
        }
        taskMapper.updateByPrimaryKeySelective(task);
        return taskBo;
    }

    @Override
    public String delete(String taskId) {
        int r = taskMapper.deleteByPrimaryKey(taskId);
        LOGGER.info("{}", r);
        return "";
    }

    @Override
    public String deleteList(String[] taskIds) {
        int r = taskMapper.deleteList(taskIds);
        LOGGER.info("{}", r);
        return "";
    }
}
