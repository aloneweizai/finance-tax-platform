package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.ChannelCount;
import org.apache.ibatis.annotations.Param;

/**
 * ChannelCountMapper数据库操作接口类
 **/

public interface ChannelCountRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    ChannelCount selectByPrimaryKey(@Param("id") Long id);


}