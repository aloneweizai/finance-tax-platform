package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.Site;
import org.apache.ibatis.annotations.Param;

/**
 * SiteMapper数据库操作接口类
 **/

public interface SiteMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("siteId") String siteId);

    /**
     * 添加
     **/
    int insert(Site record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(Site record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(Site record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(Site record);

}