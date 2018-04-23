package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.Task;
import org.apache.ibatis.annotations.Param;

/**
 * TaskMapper数据库操作接口类
 **/

public interface TaskMapper {

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("taskId") String taskId);

    /**
     * 批量删除（根据主键ID删除）
     **/
    int deleteList(@Param("taskIds") String[] taskIds);

    /**
     * 添加
     **/
    int insert(Task record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(Task record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(Task record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(Task record);

}