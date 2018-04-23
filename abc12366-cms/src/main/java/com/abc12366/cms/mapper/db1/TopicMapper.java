package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.Topic;
import org.apache.ibatis.annotations.Param;

/**
 * TopicMapper数据库操作接口类
 **/

public interface TopicMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("topicId") String topicId);

    /**
     * 批量删除（根据主键ID删除）
     **/
    int deleteList(@Param("topicIds") String[] topicIds);

    /**
     * 添加
     **/
    int insert(Topic record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(Topic record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(Topic record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(Topic record);

}