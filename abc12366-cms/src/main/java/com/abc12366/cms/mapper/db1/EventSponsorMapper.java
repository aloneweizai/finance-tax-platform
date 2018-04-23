package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.EventSponsor;
import org.apache.ibatis.annotations.Param;

/**
 * EventSponsorMapper数据库操作接口类
 **/

public interface EventSponsorMapper {

    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("sponsorId") String sponsorId);

    /**
     * 添加
     **/
    int insert(EventSponsor record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(EventSponsor record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(EventSponsor record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(EventSponsor record);

}