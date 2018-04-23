package com.abc12366.message.service;

import com.abc12366.gateway.model.BodyStatus;
import com.abc12366.message.model.BusinessBatchMessage;
import com.abc12366.message.model.BusinessMessage;
import com.abc12366.message.model.bo.BatchUpdateMsgToReadBO;
import com.abc12366.message.model.bo.BusinessMessageAdmin;

import java.util.List;
import java.util.Map;

/**
 * 用户消息服务
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-07-27 11:20 AM
 * @since 1.0.0
 */
public interface BusinessMsgService {

    List<BusinessMessage> selectList(BusinessMessage data, int page, int size);

    BusinessMessage insert(BusinessMessage data);

    /**
     * 发送批量业务消息
     */
    List<BusinessMessage> insert(BusinessBatchMessage data);

    BusinessMessage update(BusinessMessage data);

    BusinessMessage selectOne(String id);

    BodyStatus delete(BusinessMessage data);

    /**
     * 根据用户名查询业务消息
     *
     * @param map
     * @param page
     * @param size
     * @return
     */
    List<BusinessMessageAdmin> selectListByUsername(Map<String, Object> map, int page, int size);

    /**
     * 查询未读消息列表
     *
     * @param bm 业务消息条件
     * @return 业务消息未读列表
     */
    List<BusinessMessage> selectUnreadList(BusinessMessage bm);

    /**
     * 批量更新消息为已读
     * @param bo
     */
    void batchUpdateToRead(BatchUpdateMsgToReadBO bo);
}
