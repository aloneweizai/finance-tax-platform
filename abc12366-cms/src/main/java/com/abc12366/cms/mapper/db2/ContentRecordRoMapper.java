package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.ContentRecord;
import org.apache.ibatis.annotations.Param;

/**
 * ContentRecordMapper数据库操作接口类
 **/

public interface ContentRecordRoMapper {


    /**
     * 查询(根据主键ID查询)
     **/
    ContentRecord selectByPrimaryKey(@Param("id") Long id);


}