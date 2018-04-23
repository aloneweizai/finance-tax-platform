package com.abc12366.cms.mapper.db1;

import com.abc12366.cms.model.Model;
import org.apache.ibatis.annotations.Param;

/**
 * ModelMapper数据库操作接口类
 **/

public interface ModelMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("modelId") String modelId);

    /**
     * 批量删除（根据主键ID删除）
     **/
    int deleteList(@Param("modelIds") String[] modelIds);

    /**
     * 添加
     **/
    int insert(Model record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(Model record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(Model record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(Model record);

}