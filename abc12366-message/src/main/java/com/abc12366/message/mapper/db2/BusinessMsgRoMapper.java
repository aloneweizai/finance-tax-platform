package com.abc12366.message.mapper.db2;

import com.abc12366.message.model.BusinessMessage;
import com.abc12366.message.model.bo.BusinessMessageAdmin;
import com.abc12366.message.model.bo.UserSimple;

import java.util.List;
import java.util.Map;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-07-28 5:05 PM
 * @since 1.0.0
 */
public interface BusinessMsgRoMapper {
    List<BusinessMessage> selectList(BusinessMessage data);

    /**
     * 最近30天未读列表
     */
    List<BusinessMessage> selectUnreadList(BusinessMessage data);

    BusinessMessage selectOne(BusinessMessage data);

    List<BusinessMessageAdmin> selectListByUsername(Map<String, Object> map);

    /**
     * 根据用户id从用户视图查询用户简要信息
     * @param userId 用户id
     * @return UserSimple
     */
    UserSimple selectUserById(String userId);
}
