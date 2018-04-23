package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.ChannelUser;
import org.apache.ibatis.annotations.Param;

/**
 * ChannelUserMapper数据库操作接口类
 **/

public interface ChannelUserRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    ChannelUser selectByPrimaryKey(@Param("id") Long id);


}