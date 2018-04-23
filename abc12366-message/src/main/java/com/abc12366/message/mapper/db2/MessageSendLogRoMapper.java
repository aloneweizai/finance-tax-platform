package com.abc12366.message.mapper.db2;

import com.abc12366.message.model.MessageSendLog;

import java.util.List;
import java.util.Map;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-11
 * Time: 14:35
 */
public interface MessageSendLogRoMapper {

    List<MessageSendLog> selectLast();

    List<MessageSendLog> selectList(Map<String, Object> map);
}
