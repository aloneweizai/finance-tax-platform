package com.abc12366.message.mapper.db1;

import com.abc12366.message.model.UserMessage;
import com.abc12366.message.model.bo.BatchUpdateMsgToReadBO;

import java.util.List;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-07-27 3:12 PM
 * @since 1.0.0
 */
public interface UserMsgMapper {
    void insert(UserMessage data);

    void delete(String id);

    void update(UserMessage data);

    void batchInsert(List<UserMessage> dataList);

    void updateBatch(BatchUpdateMsgToReadBO bo);
}
