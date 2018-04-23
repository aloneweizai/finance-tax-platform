package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.AskLogMapper;
import com.abc12366.bangbang.model.AskLog;
import com.abc12366.bangbang.service.AskLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-27
 * Time: 15:24
 */
@Service
public class AskLogServiceImpl implements AskLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AskLogServiceImpl.class);

    @Autowired
    private AskLogMapper askLogMapper;


    @Override
    public void insert(AskLog askLog) {
        askLogMapper.insert(askLog);
    }
}
