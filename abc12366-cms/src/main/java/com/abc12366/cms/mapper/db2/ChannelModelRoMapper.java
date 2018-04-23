package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.ChannelModel;
import org.apache.ibatis.annotations.Param;

/**
 * ChannelModelMapper数据库操作接口类
 **/

public interface ChannelModelRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    ChannelModel selectByPrimaryKey(@Param("id") Long id);


}