package com.abc12366.bangbang.mapper.db2;

import com.abc12366.bangbang.model.WikiAccesslog;
import org.apache.ibatis.annotations.Param;

/**
 * WikiAccesslogMapper数据库操作接口类
 **/

public interface WikiAccesslogRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    WikiAccesslog selectByPrimaryKey(@Param("id") String id);


}