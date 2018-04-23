package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.FriendlinkCtg;
import org.apache.ibatis.annotations.Param;

/**
 * FriendlinkCtgMapper数据库操作接口类
 **/

public interface FriendlinkCtgMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") Long id);

    /**
     * 添加
     **/
    int insert(FriendlinkCtg record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(FriendlinkCtg record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(FriendlinkCtg record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(FriendlinkCtg record);

}