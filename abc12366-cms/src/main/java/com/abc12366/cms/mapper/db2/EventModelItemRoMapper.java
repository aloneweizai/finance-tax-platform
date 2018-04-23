package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.EventModelItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * EventModelItemMapper数据库操作接口类
 **/

public interface EventModelItemRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    EventModelItem selectByPrimaryKey(@Param("modelitemId") String modelitemId);

    /**
     * 查询（根据主键ID查询）
     **/
    List<EventModelItem> selectByEventId(@Param("eventId") String eventId);


}