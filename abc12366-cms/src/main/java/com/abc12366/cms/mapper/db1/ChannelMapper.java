package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.Channel;
import org.apache.ibatis.annotations.Param;

/**
 * ChannelMapper数据库操作接口类
 **/

public interface ChannelMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("channelId") String channelId);

    /**
     * 添加
     **/
    int insert(Channel record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(Channel record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(Channel record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(Channel record);

}