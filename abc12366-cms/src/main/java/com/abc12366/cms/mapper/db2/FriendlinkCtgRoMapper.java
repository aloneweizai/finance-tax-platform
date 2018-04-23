package com.abc12366.cms.mapper.db2;

import com.abc12366.cms.model.FriendlinkCtg;
import org.apache.ibatis.annotations.Param;

/**
 * FriendlinkCtgMapper数据库操作接口类
 **/

public interface FriendlinkCtgRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    FriendlinkCtg selectByPrimaryKey(@Param("id") Long id);


}