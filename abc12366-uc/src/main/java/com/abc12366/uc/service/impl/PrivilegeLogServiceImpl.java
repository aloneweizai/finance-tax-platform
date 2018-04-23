package com.abc12366.uc.service.impl;

import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.PrivilegeLogMapper;
import com.abc12366.uc.mapper.db2.PrivilegeLogRoMapper;
import com.abc12366.uc.model.PrivilegeLog;
import com.abc12366.uc.service.PrivilegeLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-25
 * Time: 11:51
 */
@Service
public class PrivilegeLogServiceImpl implements PrivilegeLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeLogServiceImpl.class);

    @Autowired
    private PrivilegeLogMapper privilegeLogMapper;

    @Autowired
    private PrivilegeLogRoMapper privilegeLogRoMapper;

    @Override
    public PrivilegeLog insert(PrivilegeLog privilegeLog) {
        LOGGER.info("{}", privilegeLog);
        privilegeLog.setId(Utils.uuid());
        privilegeLog.setCreateTime(new Date());
        int i = privilegeLogMapper.insert(privilegeLog);
        return privilegeLog;
    }

    @Override
    public List<PrivilegeLog> selectListMonth(String logType, String userId) {
        LOGGER.info("{}:{}", logType, userId);
        return privilegeLogRoMapper.selectListMonth(logType, userId);
    }

    @Override
    public List<PrivilegeLog> selectListYear(String logType, String userId) {
        LOGGER.info("{}:{}", logType, userId);
        return privilegeLogRoMapper.selectListYear(logType, userId);
    }

    @Override
    public List<PrivilegeLog> selectList(String logType, String userId) {
        LOGGER.info("{}:{}", logType, userId);
        return privilegeLogRoMapper.selectList(logType, userId);
    }
}
