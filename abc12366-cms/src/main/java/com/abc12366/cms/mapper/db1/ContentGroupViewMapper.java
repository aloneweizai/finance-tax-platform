package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.ContentGroupView;
import org.apache.ibatis.annotations.Param;

/**
 * ContentGroupViewMapper数据库操作接口类
 **/

public interface ContentGroupViewMapper {

    /**
     * 删除(根据主键ID删除)
     **/
    int deleteByPrimaryKey(@Param("contentId") String contentId);

    /**
     * 添加
     **/
    int insert(ContentGroupView record);

    /**
     * 添加(匹配有值的字段)
     **/
    int insertSelective(ContentGroupView record);

    /**
     * 修改(匹配有值的字段)
     **/
    int updateByPrimaryKeySelective(ContentGroupView record);

    /**
     * 修改(根据主键ID修改)
     **/
    int updateByPrimaryKey(ContentGroupView record);

}