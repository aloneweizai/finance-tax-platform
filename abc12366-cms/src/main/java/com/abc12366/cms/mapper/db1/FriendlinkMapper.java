package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.Friendlink;
import org.apache.ibatis.annotations.Param;

/**
 * FriendlinkMapper数据库操作接口类
 **/

public interface FriendlinkMapper {

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("friendlinkId") String friendlinkId);

    /**
     * 添加
     **/
    int insert(Friendlink record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(Friendlink record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(Friendlink record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(Friendlink record);

}