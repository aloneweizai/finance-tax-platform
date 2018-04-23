package com.abc12366.bangbang.service.impl;

import com.abc12366.bangbang.mapper.db1.QuestionLogMapper;
import com.abc12366.bangbang.model.question.QuestionLog;
import com.abc12366.bangbang.service.QueLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xieyanmao on 2017/9/15.
 */
@Service
public class QueLogServiceImpl implements QueLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueLogServiceImpl.class);

    @Autowired
    private QuestionLogMapper questionLogMapper;

    @Override
    public QuestionLog insert(QuestionLog questionLog) {

        questionLogMapper.insert(questionLog);
        return questionLog;
    }


}
