package com.abc12366.uc.service.admin;

import com.abc12366.uc.model.admin.Admin;
import com.abc12366.uc.model.admin.AdminExtend;
import com.abc12366.uc.model.admin.bo.AdminBO;
import com.abc12366.uc.model.admin.bo.AdminUpdateBO;
import com.abc12366.uc.model.admin.bo.LoginInfoBO;
import com.abc12366.uc.model.admin.bo.UserPasswordBO;

import java.util.List;

/**
 * 用户管理
 *
 * @author：lizhongwei
 */
public interface AdminService {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return Admin
     */
    Admin selectUserByLoginName(String username);

    /**
     * 根据用户id查询用户
     *
     * @param id PK
     * @return Admin
     */
    Admin selectUserById(String id);

    /**
     * 用户列表
     *
     * @param admin Admin
     * @return List<AdminBO>
     */
    List<AdminBO> selectUser(Admin admin);


    /**
     * 用户列表
     *
     * @param admin AdminBO
     * @return List<AdminBO>
     */
    List<AdminBO> selectList(AdminBO admin);

    /**
     * 添加用户
     *
     * @param adminBO AdminBO
     * @return int
     */
    int register(AdminBO adminBO);

    /**
     * 修改密码
     *
     * @param userId 用户ID
     * @param pwd    密码
     */
    void updateUserPwdById(String userId, String pwd);

    /**
     * 根据用户id查询用户带部门
     *
     * @param id 用户ID
     * @return AdminBO
     */
    AdminBO selectUserVOById(String id);

    /**
     * 修改用户
     *
     * @param adminUpdateBO AdminUpdateBO
     * @return AdminUpdateBO
     */
    AdminUpdateBO updateUser(AdminUpdateBO adminUpdateBO);

    /**
     * 删除用户
     *
     * @param id pk
     * @return int
     */
    int deleteUserById(String id);

    /**
     * 查看用户
     *
     * @param id pk
     * @return AdminBO
     */
    AdminBO selectOne(String id);

    /**
     * 用户登陆
     *
     * @param adminBO AdminBO
     * @param appId   用户ID
     * @return AdminBO
     */
    AdminBO login(AdminBO adminBO, String appId);

    /**
     * 查看用户扩展信息
     *
     * @param id pk
     * @return AdminExtend
     */
    AdminExtend selectUserExtendByUserId(String id);

    /**
     * 判断该token是否存在
     *
     * @param userToken 用户令牌
     * @return boolean
     */
    boolean isAuthentication(String userToken);

    /**
     * 新增用户
     *
     * @param adminBO AdminBO
     * @return AdminBO
     */
    AdminBO addUser(AdminBO adminBO);

    /**
     * 登出
     *
     * @param token 令牌
     */
    void logout(String token);

    /**
     * 修改用户密码
     *
     * @param userPasswordBO UserPasswordBO
     * @return int
     */
    int updateUserPwd(UserPasswordBO userPasswordBO);

    /**
     * 重置用户密码
     *
     * @param id pk
     * @return int
     */
    int resetUserPwd(String id);

    /**
     * 禁用所以用户
     */
    void disableAll();

    /**
     * 启用用户
     *
     * @param adminUpdateBO AdminUpdateBO
     */
    void enable(AdminUpdateBO adminUpdateBO);

    /**
     * 校验token
     *
     * @param token String
     * @return Boolean
     */
    Boolean checkToken(String token);

    /**
     * 刷新token
     *
     * @param token String
     * @return Boolean
     */
    Boolean refreshToken(String token);

    /**
     * 通过token查询用户信息
     *
     * @param token String
     * @return LoginInfoBO
     */
    LoginInfoBO selectLoginInfoByToken(String token);
}
