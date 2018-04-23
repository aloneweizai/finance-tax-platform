package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.admin.Admin;
import com.abc12366.uc.model.admin.bo.AdminBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserMapper数据库操作接口类
 **/

public interface AdminRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Admin selectByPrimaryKey(@Param("id") String id);


    Admin selectUserByLoginName(String username);

    AdminBO selectUserBOByLoginName(String username);

    Admin selectUserById(String id);

    AdminBO selectUserBoById(String id);

    List<AdminBO> selectList(AdminBO admin);

    AdminBO selectOne(@Param("id") String id);

    List<Admin> selectAdminByOrgId(@Param("orgId") String orgId);
}