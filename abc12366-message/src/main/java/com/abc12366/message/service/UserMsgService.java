package com.abc12366.message.service;

import com.abc12366.gateway.model.BodyStatus;
import com.abc12366.message.model.UserBatchMessage;
import com.abc12366.message.model.UserMessage;
import com.abc12366.message.model.bo.BatchUpdateMsgToReadBO;
import com.abc12366.message.model.bo.UserMessageAdmin;
import com.abc12366.message.model.bo.UserMessageForBangbang;

import java.util.List;
import java.util.Map;

/**
 * 用户消息服务
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-07-27 11:20 AM
 * @since 1.0.0
 */
public interface UserMsgService {

    /**
     * 获取用户私信列表：帮帮用
     *
     * @param data 用户消息对象，userId
     * @param page 当前页
     * @param size 每页大小
     * @return 用户帮帮消息列表
     */
    List<UserMessage> selectList(UserMessage data, int page, int size);

    /**
     * 新增用户消息
     *
     * @param data 消息对象
     * @return 消息对象
     */
    UserMessage insert(UserMessage data);

    /**
     * 直接将'未读'消息置为'已读'，不需要进入消息
     *
     * @param data 消息对象
     * @return 消息对象
     */
    UserMessage update(UserMessage data);

    /**
     * 用户查看消息，如果消息状态为'未读'，则将消息状态置为'已读'
     *
     * @param id 消息ID
     * @return 消息对象
     */
    UserMessage selectOne(String id);

    /**
     * 用户删除消息，物理删除
     *
     * @param data 消息对象，ID，userId
     * @return 删除结果
     */
    BodyStatus delete(UserMessage data);

    /**
     * 批量发送用户消息
     *
     * @param data 用户消息对象,用户ID为集合
     * @return 用户消息列表
     */
    List<UserMessage> insert(UserBatchMessage data);

    /**
     * 获取用户私信列表：帮帮用
     *
     * @param userId 用户ID
     * @param page   当前页
     * @param size   每页大小
     * @return 用户帮帮消息列表
     */
    List<UserMessageForBangbang> selectListForBangbang(String userId, int page, int size);

    /**
     * 根据用户名查询发给这个用户的消息
     *
     * @param map fromUserId,toUserId,type
     * @return 会话列表
     */
    List<UserMessageAdmin> selectConversationList(Map<String, Object> map, int page, int size);

    /**
     * 获取未读消息列表
     *
     * @param um 查询条件
     * @return 未读消息列表
     */
    List<UserMessage> selectUnreadList(UserMessage um);

    /**
     * 批量更新用户消息为“已读”
     * @param bo
     */
    void batchUpdateToRead(BatchUpdateMsgToReadBO bo);
}
