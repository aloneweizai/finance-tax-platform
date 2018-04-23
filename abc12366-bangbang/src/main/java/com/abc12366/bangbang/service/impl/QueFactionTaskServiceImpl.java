package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db2.QuestionFactionTaskRoMapper;
import com.abc12366.bangbang.model.question.bo.QuestionFactionTaskBo;
import com.abc12366.bangbang.service.QueFactionTaskService;
import com.abc12366.gateway.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyanmao on 2017/9/14.
 */
@Service
public class QueFactionTaskServiceImpl implements QueFactionTaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueFactionTaskServiceImpl.class);

    @Autowired
    private QuestionFactionTaskRoMapper taskRoMapper;

    @Override
    public List<QuestionFactionTaskBo> selectListdt() {
        List<QuestionFactionTaskBo> taskBoList;
        try {
            //查询邦派动态列表
            taskBoList = taskRoMapper.selectListdt();
        } catch (Exception e) {
            LOGGER.error("查询邦派动态列表信息异常：{}", e);
            throw new ServiceException(6120);
        }
        return taskBoList;
    }

    @Override
    public List<QuestionFactionTaskBo> selectTaskList(Map map) {
        List<QuestionFactionTaskBo> taskBoList;
        try {
            //查询邦派任务情况
            taskBoList = taskRoMapper.selectTaskList(map);
        } catch (Exception e) {
            LOGGER.error("查询邦派任务情况信息异常：{}", e);
            throw new ServiceException(6120);
        }
        return taskBoList;
    }

}
