package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.SystemRecord;
import com.abc12366.bangbang.model.bo.SystemRecordBO;
import com.abc12366.gateway.model.bo.TableBO;

public interface SystemRecordMapper {

    int insert(SystemRecord systemRecord);

    int updateStay(SystemRecordBO systemRecordBO);

    /**
     * 如果当天的表不存在，自动创建表
     * @param tableBO 根据年月日建表
     */
    void create(TableBO tableBO);
}
