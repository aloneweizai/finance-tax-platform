package com.abc12366.message.mapper.db1;

import com.abc12366.message.model.MessageSendLog;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-11
 * Time: 14:35
 */
public interface MessageSendLogMapper {
    int insert(MessageSendLog sendLog);

    int update(MessageSendLog sendLog);
}
