package com.abc12366.message.mapper.db1;

import com.abc12366.message.model.BusinessMessage;
import com.abc12366.message.model.bo.BatchUpdateMsgToReadBO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-07-28 5:04 PM
 * @since 1.0.0
 */
public interface BusinessMsgMapper {
    void insert(BusinessMessage data);

    void update(BusinessMessage data);

    void batchInsert(@Param("list")List<BusinessMessage> dataList,@Param("dateStr") String dateStr);

    void updateBatch(BatchUpdateMsgToReadBO bo);

	void createTable(String dateStr);
}
