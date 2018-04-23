package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.ContentTopic;
import org.apache.ibatis.annotations.Param;

/**
 * ContentTopicMapper数据库操作接口类
 **/

public interface ContentTopicMapper {

    /**
     * 删除(根据主键ID删除)
     **/
    int deleteByPrimaryKey(ContentTopic record);

    /**
     * 删除(根据主键ID删除)
     **/
    int deleteByContentId(@Param("contentId") String contentId);

    /**
     * 添加
     **/
    int insert(ContentTopic record);

    /**
     * 添加(匹配有值的字段)
     **/
    int insertSelective(ContentTopic record);

    /**
     * 修改(匹配有值的字段)
     **/
    int updateByPrimaryKeySelective(ContentTopic record);

    /**
     * 修改(根据主键ID修改)
     **/
    int updateByPrimaryKey(ContentTopic record);

}