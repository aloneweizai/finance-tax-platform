package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.ContentCount;
import org.apache.ibatis.annotations.Param;

/**
 * ContentCountMapper数据库操作接口类
 **/

public interface ContentCountMapper {

    /**
     * 删除(根据主键ID删除)
     **/
    int deleteByPrimaryKey(@Param("id") Long id);

    /**
     * 添加
     **/
    int insert(ContentCount record);

    /**
     * 添加(匹配有值的字段)
     **/
    int insertSelective(ContentCount record);

    /**
     * 修改(匹配有值的字段)
     **/
    int updateByPrimaryKeySelective(ContentCount record);

    /**
     * 修改(根据主键ID修改)
     **/
    int updateByPrimaryKey(ContentCount record);

}