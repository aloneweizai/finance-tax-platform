package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.ContentExt;
import org.apache.ibatis.annotations.Param;

/**
 * ContentExtMapper数据库操作接口类
 **/

public interface ContentExtRoMapper {


    /**
     * 查询(根据主键ID查询)
     **/
    ContentExt selectByPrimaryKey(@Param("contentId") String contentId);

    /**
     * 查询(根据contentId查询)
     **/
    ContentExt selectByContentId(String contentId);


}