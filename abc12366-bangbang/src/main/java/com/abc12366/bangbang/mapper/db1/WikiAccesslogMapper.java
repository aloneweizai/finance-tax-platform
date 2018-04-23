package com.abc12366.bangbang.mapper.db1;

import com.abc12366.bangbang.model.WikiAccesslog;
import org.apache.ibatis.annotations.Param;

/**
 * WikiAccesslogMapper数据库操作接口类
 **/

public interface WikiAccesslogMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(WikiAccesslog record);

    /**
     * 修改（根据主键ID修改）
     **/
    int update(WikiAccesslog record);

}