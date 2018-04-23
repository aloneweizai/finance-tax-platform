package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.Origin;
import org.apache.ibatis.annotations.Param;

/**
 * OriginMapper数据库操作接口类
 **/

public interface OriginRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Origin selectByPrimaryKey(@Param("id") Long id);


}