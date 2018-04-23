package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.Plug;
import org.apache.ibatis.annotations.Param;

/**
 * PlugMapper数据库操作接口类
 **/

public interface PlugRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Plug selectByPrimaryKey(@Param("id") Long id);


}