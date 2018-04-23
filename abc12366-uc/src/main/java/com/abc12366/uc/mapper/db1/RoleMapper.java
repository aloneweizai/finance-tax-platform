package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.admin.Role;
import org.apache.ibatis.annotations.Param;

/**
 * RoleMapper数据库操作接口类
 **/

public interface RoleMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteByPrimaryKey(@Param("id") Long id);

    /**
     * 添加
     **/
    int insert(Role record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(Role record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(Role record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(Role record);

    int deleteRoleById(String id);

    int updateRole(Role role);


}