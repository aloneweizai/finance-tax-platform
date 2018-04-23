package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.ContentExt;
import org.apache.ibatis.annotations.Param;

/**
 * ContentExtMapper数据库操作接口类
 **/

public interface ContentExtMapper {

    /**
     * 删除(根据主键ID删除)
     **/
    int deleteByPrimaryKey(@Param("contentId") String contentId);

    /**
     * 添加
     **/
    int insert(ContentExt record);

    /**
     * 添加(匹配有值的字段)
     **/
    int insertSelective(ContentExt record);

    /**
     * 修改(匹配有值的字段)
     **/
    int updateByPrimaryKeySelective(ContentExt record);

    /**
     * 修改(根据主键ID修改)
     **/
    int updateByPrimaryKey(ContentExt record);

    /**
     * 修改是否已生成静态页为是(根据主键ID修改)
     **/
    int updatRegenerateList(@Param("contentIds") String[] contentIds);

    /**
     * 修改是否已生成静态页为否(根据主键ID修改)
     **/
    int updatRegenerateList0(@Param("contentIds") String[] contentIds);

    /**
     * 清空发布日期(根据主键ID修改)
     **/
    int updateReleaseDate(@Param("contentIds") String[] contentIds);

    /**
     * 更新发布日期(根据主键ID修改)
     **/
    int updateReleaseDate2(@Param("contentId") String contentId);

}