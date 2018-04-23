package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.EventRecord;
import org.apache.ibatis.annotations.Param;

/**
 * EventRecordMapper数据库操作接口类
 **/

public interface EventRecordMapper {

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("recordId") String recordId);

    /**
     * 添加
     **/
    int insert(EventRecord record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(EventRecord record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(EventRecord record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(EventRecord record);

}