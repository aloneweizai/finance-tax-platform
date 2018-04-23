package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.ContentChannel;
import org.apache.ibatis.annotations.Param;

/**
 * ContentChannelMapper数据库操作接口类
 **/

public interface ContentChannelMapper {

    /**
     * 删除(根据主键ID删除)
     **/
    int deleteByPrimaryKey(@Param("id") Long id);

    /**
     * 添加
     **/
    int insert(ContentChannel record);

    /**
     * 添加(匹配有值的字段)
     **/
    int insertSelective(ContentChannel record);

    /**
     * 修改(匹配有值的字段)
     **/
    int updateByPrimaryKeySelective(ContentChannel record);

    /**
     * 修改(根据主键ID修改)
     **/
    int updateByPrimaryKey(ContentChannel record);

}