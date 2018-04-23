package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.Content;
import org.apache.ibatis.annotations.Param;

/**
 * ContentMapper数据库操作接口类
 **/

public interface ContentMapper {

    /**
     * 删除(根据主键ID删除)
     **/
    int deleteByPrimaryKey(@Param("contentId") String contentId);

    /**
     * 添加
     **/
    int insert(Content record);

    /**
     * 添加(匹配有值的字段)
     **/
    int insertSelective(Content record);

    /**
     * 修改(匹配有值的字段)
     **/
    int updateByPrimaryKeySelective(Content record);

    /**
     * 修改(根据主键ID修改)
     **/
    int updateByPrimaryKey(Content record);

    /**
     * 修改状态(根据主键ID修改)
     **/
    int updateStatusList(@Param("contentIds") String[] contentIds);

    /**
     * 修改浏览量(根据主键ID)
     **/
    int updateViewsDay(@Param("contentId") String contentId);

    /**
     * 文章发布(根据主键ID)
     **/
    int updateStatus2(@Param("contentId") String contentId);

}