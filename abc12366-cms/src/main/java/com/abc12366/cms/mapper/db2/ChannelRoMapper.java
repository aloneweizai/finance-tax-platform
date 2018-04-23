package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.Channel;
import com.abc12366.cms.model.bo.ChannelBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ChannelMapper数据库操作接口类
 **/

public interface ChannelRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Channel selectByPrimaryKey(@Param("channelId") String channelId);

    List<Channel> selectListByParam(Channel channel);

    List<Channel> selectList();

    List<ChannelBo> selectLists(Map<String, Object> map);

    List<Channel> selectListByparentId(@Param("parentId") String parentId);

    Integer selectChannelIdCnt(@Param("channelId") String channelId);

    Integer selectChannelPathCnt(ChannelBo channelBo);

}