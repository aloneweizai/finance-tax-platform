package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.ChannelExt;
import org.apache.ibatis.annotations.Param;

/**
 * ChannelExtMapper数据库操作接口类
 **/

public interface ChannelExtMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("channelId") String channelId);

    /**
     * 添加
     **/
    int insert(ChannelExt record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(ChannelExt record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(ChannelExt record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(ChannelExt record);

}