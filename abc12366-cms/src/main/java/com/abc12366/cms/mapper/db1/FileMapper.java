package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.File;
import org.apache.ibatis.annotations.Param;

/**
 * FileMapper数据库操作接口类
 **/

public interface FileMapper {

    /**
     * 删除(根据主键ID删除)
     **/
    int deleteByPrimaryKey(@Param("contentId") String contentId);

    /**
     * 添加
     **/
    int insert(File record);

    /**
     * 添加(匹配有值的字段)
     **/
    int insertSelective(File record);

    /**
     * 修改(匹配有值的字段)
     **/
    int updateByPrimaryKeySelective(File record);

    /**
     * 修改(根据主键ID修改)
     **/
    int updateByPrimaryKey(File record);

    /**
     * 更新文件为1(无效状态)
     **/
    int updateByContentId(String contentId);

    /**
     * 删除(根据主键ID删除)
     **/
    int deleteByContentId(String contentId);

}