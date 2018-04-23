package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.Event;
import com.abc12366.cms.model.bo.EventListBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * EventMapper数据库操作接口类
 **/

public interface EventRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Event selectByPrimaryKey(@Param("eventId") String eventId);

    /**
     * 查询（根据主键ID查询）
     **/
    Event selecttopone(@Param("category") String category);

    /**
     * 查询（根据条件查询）
     **/
    List<EventListBo> selectList(Map<String, Object> map);


}