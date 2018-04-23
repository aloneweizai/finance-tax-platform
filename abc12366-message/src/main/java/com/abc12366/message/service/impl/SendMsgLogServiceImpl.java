package com.abc12366.message.service.impl;

import com.abc12366.gateway.util.Utils;
import com.abc12366.message.mapper.db1.MessageSendLogMapper;
import com.abc12366.message.mapper.db2.MessageSendLogRoMapper;
import com.abc12366.message.model.MessageSendLog;
import com.abc12366.message.service.SendMsgLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-24
 * Time: 11:18
 */
@Service
public class SendMsgLogServiceImpl implements SendMsgLogService {

    @Autowired
    private MessageSendLogMapper sendLogMapper;

    @Autowired
    private MessageSendLogRoMapper sendLogRoMapper;

    @Override
    public MessageSendLog insert(MessageSendLog sendLog) {
        sendLog.setId(Utils.uuid());
        Date date = new Date();
        sendLog.setSendtime(date);
        sendLog.setLogtime(date);
        sendLogMapper.insert(sendLog);
        return sendLog;
    }

    @Override
    public List<MessageSendLog> selectList(Map<String, Object> map) {
        return sendLogRoMapper.selectList(map);
    }

    @Override
    public int update(MessageSendLog sendLog) {
        return sendLogMapper.update(sendLog);
    }
}
