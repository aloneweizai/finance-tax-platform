package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.EventModelItem;
import org.apache.ibatis.annotations.Param;

/**
 * EventModelItemMapper数据库操作接口类
 **/

public interface EventModelItemMapper {

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("modelitemId") String modelitemId);

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByEventId(@Param("eventId") String eventId);

    /**
     * 添加
     **/
    int insert(EventModelItem record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(EventModelItem record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(EventModelItem record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(EventModelItem record);

}