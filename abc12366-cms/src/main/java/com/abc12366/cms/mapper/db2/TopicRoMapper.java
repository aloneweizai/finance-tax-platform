package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.Topic;
import com.abc12366.cms.model.bo.TopicBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * TopicMapper数据库操作接口类
 **/

public interface TopicRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Topic selectByPrimaryKey(@Param("topicId") String topicId);

    /**
     * 查询(根据查询条件查询)
     **/
    List<TopicBo> selectList(Map<String, Object> map);

    /**
     * 查询(根据查询条件查询)
     **/
    List<TopicBo> selectListBytplContent(@Param("tplContent") String tplContent);

}