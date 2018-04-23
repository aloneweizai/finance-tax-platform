package com.abc12366.uc.mapper.db2;

import com.abc12366.uc.model.admin.Role;
import com.abc12366.uc.model.admin.bo.RoleBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * RoleMapper数据库操作接口类
 **/

public interface RoleRoMapper {


    /**
     * 查询（根据主键ID查询）
     **/
    Role selectByPrimaryKey(@Param("id") String id);

    List<RoleBO> selectList(Role role);


    RoleBO selectRoleById(String id);

    List<String> selectMenuIdListByRoleId(String id);

    List<String> selectRoleMenuIdListByRoleId(String id);

    List<Map<String, String>> selectRoleResourceListByRoleId(String roleId);

    Role selectOne(Role role);

    Role selectRoleByName(Role role);

    RoleBO selectUserByRoleId(String id);
}