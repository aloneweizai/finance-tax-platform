package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.ContentAttachment;
import org.apache.ibatis.annotations.Param;

/**
 * ContentAttachmentMapper数据库操作接口类
 **/

public interface ContentAttachmentRoMapper {


    /**
     * 查询(根据主键ID查询)
     **/
    ContentAttachment selectByPrimaryKey(@Param("id") Long id);


}