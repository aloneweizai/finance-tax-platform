package com.abc12366.uc.service.admin;

import com.abc12366.uc.model.admin.Role;
import com.abc12366.uc.model.admin.bo.RoleBO;
import com.abc12366.uc.model.admin.bo.RoleUpdateBO;
import com.abc12366.uc.model.admin.bo.UserRoleBO;

import java.util.List;
import java.util.Map;

/**
 * 权限管理
 *
 * @author lizhongwei
 * @create 2017-05-03 10:08 AM
 * @since 1.0.0
 */
public interface RoleService {

    /**
     * 查询权限树
     *
     * @param role Role
     * @return List<RoleBO>
     */
    List<RoleBO> selectList(Role role);

    /**
     * 添加权限
     *
     * @param roleBO RoleBO
     * @return Role
     */
    Role addRole(RoleBO roleBO);

    /**
     * 根据id删除权限
     *
     * @param id pk
     * @return int
     */
    int deleteRoleById(String id);

    /**
     * 根据id查询权限
     *
     * @param id pk
     * @return RoleBO
     */
    RoleBO selectRoleById(String id);

    /**
     * 更新权限
     *
     * @param roleBO RoleBO
     * @return RoleBO
     */
    RoleBO updateRole(RoleBO roleBO);

    /**
     * 根据权限id查询资源集合
     *
     * @param id pk
     * @return List<String>
     */
    List<String> selectMenuIdListByRoleId(String id);

    /**
     * 更新权限和资源的关联关系
     *
     * @param id 角色ID
     * @param menuIds 菜单ids字符串
     */
    void updateRoleMenu(String id, String menuIds);

    /**
     * 根据用户查询id查询权限集合
     *
     * @param userId 用户ID
     * @return List<String>
     */
    List<String> selectRoleIdListByUserId(String userId);

    /**
     * 根据权限查询id查询资源路径集合
     *
     * @param roleId 角色ID
     * @return List<Map<String, String>>
     */
    List<Map<String, String>> selectRoleResourceListByRoleId(String roleId);


    /**
     * @param roleBO RoleBO
     * @return Role
     */
    Role selectOne(RoleBO roleBO);

    /**
     * 根据角色名称查询角色
     *
     * @param roleBO RoleBO
     * @return Role
     */
    Role selectRoleByName(RoleBO roleBO);

    /**
     * 根据角色ID查询用户
     * @param id 角色ID
     * @return RoleBO
     */
    RoleBO selectUserByRoleId(String id);

    /**
     * gen
     * @param userRoleBO
     * @return
     */
    UserRoleBO updateUserRole(UserRoleBO userRoleBO);

    void enable(RoleUpdateBO roleUpdateBO);
}
