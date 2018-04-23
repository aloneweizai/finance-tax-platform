package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.ContentAttachment;
import org.apache.ibatis.annotations.Param;

/**
 * ContentAttachmentMapper数据库操作接口类
 **/

public interface ContentAttachmentMapper {


    /**
     * 删除(根据主键ID删除)
     **/
    int deleteByPrimaryKey(@Param("id") Long id);

    /**
     * 添加
     **/
    int insert(ContentAttachment record);

    /**
     * 添加(匹配有值的字段)
     **/
    int insertSelective(ContentAttachment record);

    /**
     * 修改(匹配有值的字段)
     **/
    int updateByPrimaryKeySelective(ContentAttachment record);

    /**
     * 修改(根据主键ID修改)
     **/
    int updateByPrimaryKey(ContentAttachment record);

}