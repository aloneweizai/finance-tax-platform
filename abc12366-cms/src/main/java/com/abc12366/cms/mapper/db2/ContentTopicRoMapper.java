package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.ContentTopic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ContentTopicMapper数据库操作接口类
 **/

public interface ContentTopicRoMapper {


    /**
     * 查询(根据主键ID查询)
     **/
    List<ContentTopic> selectByPrimaryKey(@Param("contentId") String contentId);


}