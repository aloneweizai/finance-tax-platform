package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.EventRecord;
import org.apache.ibatis.annotations.Param;

/**
 * EventRecordMapper数据库操作接口类
 **/

public interface EventRecordRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    EventRecord selectByPrimaryKey(@Param("recordId") String recordId);


}