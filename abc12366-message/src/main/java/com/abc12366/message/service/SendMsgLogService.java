package com.abc12366.message.service;

import com.abc12366.message.model.MessageSendLog;

import java.util.List;
import java.util.Map;

/**
 * 短信日志
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-24
 * Time: 11:18
 */
public interface SendMsgLogService {
    /**
     * 新增短信日志
     *
     */
    MessageSendLog insert(MessageSendLog sendLog);

    /**
     * 查询短信日志列表
     *
     */
    List<MessageSendLog> selectList(Map<String, Object> map);

    /**
     * 更新日志记录
     * @param sendLog 日志实体
     * @return 更新数据行数
     */
    int update(MessageSendLog sendLog);
}
