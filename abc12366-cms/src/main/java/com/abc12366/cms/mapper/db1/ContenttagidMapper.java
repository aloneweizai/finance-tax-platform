package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.Contenttagid;
import org.apache.ibatis.annotations.Param;

/**
 * ContenttagidMapper数据库操作接口类
 **/

public interface ContenttagidMapper {

    /**
     * 删除(根据主键ID删除)
     **/
    int deleteByPrimaryKey(@Param("contentId") String contentId);

    /**
     * 添加
     **/
    int insert(Contenttagid record);

    /**
     * 添加(匹配有值的字段)
     **/
    int insertSelective(Contenttagid record);

    /**
     * 修改(匹配有值的字段)
     **/
    int updateByPrimaryKeySelective(Contenttagid record);

    /**
     * 修改(根据主键ID修改)
     **/
    int updateByPrimaryKey(Contenttagid record);

}