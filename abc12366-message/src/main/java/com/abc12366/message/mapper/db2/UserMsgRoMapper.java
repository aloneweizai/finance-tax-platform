package com.abc12366.message.mapper.db2;

import com.abc12366.message.model.UserMessage;
import com.abc12366.message.model.bo.UserMessageAdmin;
import com.abc12366.message.model.bo.UserMessageForBangbang;
import com.abc12366.message.model.bo.UserSimple;

import java.util.List;
import java.util.Map;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-07-27 2:57 PM
 * @since 1.0.0
 */
public interface UserMsgRoMapper {
    List<UserMessage> selectList(UserMessage data);

    UserMessage selectOne(String id);

    List<UserMessageForBangbang> UserMessageForBangbang(String userId);

    /**
     * 用户会话
     */
    List<UserMessageAdmin> selectConversationList(Map<String, Object> map);

    /**
     * 最近30天未读列表
     *
     * @param data 过滤条件
     * @return 未读列表
     */
    List<UserMessage> selectUnreadList(UserMessage data);

    /**
     * 根据ID查询用户图片、昵称
     */
    UserSimple selectUserById(String id);
}
