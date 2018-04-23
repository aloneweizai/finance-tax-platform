package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.admin.UserRole;
import org.apache.ibatis.annotations.Param;

/**
 * UserRoleMapper数据库操作接口类
 **/

public interface UserRoleMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteById(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(UserRole record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(UserRole record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(UserRole record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(UserRole record);

    void deleteByRoleId(String roleId);
}