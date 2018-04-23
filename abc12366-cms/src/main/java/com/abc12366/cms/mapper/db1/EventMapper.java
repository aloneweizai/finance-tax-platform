package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.Event;
import org.apache.ibatis.annotations.Param;

/**
 * EventMapper数据库操作接口类
 **/

public interface EventMapper {

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("eventId") String eventId);

    /**
     * 添加
     **/
    int insert(Event record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(Event record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(Event record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(Event record);

    void updateStatus();

}