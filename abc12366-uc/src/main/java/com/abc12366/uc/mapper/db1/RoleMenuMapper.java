package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.admin.RoleMenu;
import org.apache.ibatis.annotations.Param;

/**
 * RoleMenuMapper数据库操作接口类
 **/

public interface RoleMenuMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(RoleMenu record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(RoleMenu record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(RoleMenu record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(RoleMenu record);


    void deleteById(String roleMenuId);

    int deleteByRoleId(String id);
}