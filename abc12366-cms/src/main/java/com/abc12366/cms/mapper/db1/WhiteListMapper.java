package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.questionnaire.WhiteList;
import org.apache.ibatis.annotations.Param;

/**
 * WhiteListMapper数据库操作接口类
 **/

public interface WhiteListMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(WhiteList record);


    /**
     * 修改（根据主键ID修改）
     **/
    int update(WhiteList record);

}