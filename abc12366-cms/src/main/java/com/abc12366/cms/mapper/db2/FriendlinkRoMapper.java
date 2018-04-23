package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.Friendlink;
import com.abc12366.cms.model.bo.FriendlinkBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * FriendlinkMapper数据库操作接口类
 **/

public interface FriendlinkRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Friendlink selectByPrimaryKey(@Param("friendlinkId") String friendlinkId);

    /**
     * 查询所有
     **/
    List<FriendlinkBo> selectList();

}