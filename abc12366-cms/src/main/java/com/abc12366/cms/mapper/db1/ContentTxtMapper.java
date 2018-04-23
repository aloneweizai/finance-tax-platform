package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.ContentTxt;
import org.apache.ibatis.annotations.Param;

/**
 * ContentTxtMapper数据库操作接口类
 **/

public interface ContentTxtMapper {

    /**
     * 删除(根据主键ID删除)
     **/
    int deleteByPrimaryKey(@Param("contentId") String contentId);

    /**
     * 添加
     **/
    int insert(ContentTxt record);

    /**
     * 添加(匹配有值的字段)
     **/
    int insertSelective(ContentTxt record);

    /**
     * 修改(匹配有值的字段)
     **/
    int updateByPrimaryKeySelective(ContentTxt record);

    /**
     * 修改(根据主键ID修改)
     **/
    int updateByPrimaryKey(ContentTxt record);

}