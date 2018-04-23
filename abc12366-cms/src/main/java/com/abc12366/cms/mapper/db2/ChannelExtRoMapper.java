package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.ChannelExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ChannelExtMapper数据库操作接口类
 **/

public interface ChannelExtRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    ChannelExt selectByPrimaryKey(@Param("channelId") String channelId);

    List<ChannelExt> selectListBytplChannel(@Param("tplChannel") String tplChannel);


}