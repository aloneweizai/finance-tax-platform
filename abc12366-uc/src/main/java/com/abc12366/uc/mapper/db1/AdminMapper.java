package com.abc12366.uc.mapper.db1;

import com.abc12366.uc.model.admin.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * UserMapper数据库操作接口类
 **/

public interface AdminMapper {


    /**
     * 删除（根据主键ID删除）
     **/
    int deleteById(@Param("id") String id);

    /**
     * 添加
     **/
    int insert(Admin record);

    /**
     * 添加 （匹配有值的字段）
     **/
    int insertSelective(Admin record);

    /**
     * 修改 （匹配有值的字段）
     **/
    int updateByPrimaryKeySelective(Admin record);

    /**
     * 修改（根据主键ID修改）
     **/
    int updateByPrimaryKey(Admin record);


    int updateUserPwdById(@Param("id") String id, @Param("password") String password);

    int updateUser(Admin admin);

}