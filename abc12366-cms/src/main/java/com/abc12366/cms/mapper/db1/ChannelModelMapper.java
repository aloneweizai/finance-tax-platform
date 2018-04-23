package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.ChannelModel;
import org.apache.ibatis.annotations.Param;

/**
 * ChannelModelMapper数据库操作接口类
 **/

public interface ChannelModelMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") Long id);

    /**
     * 添加
     **/
    int insert(ChannelModel record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(ChannelModel record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(ChannelModel record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(ChannelModel record);

}