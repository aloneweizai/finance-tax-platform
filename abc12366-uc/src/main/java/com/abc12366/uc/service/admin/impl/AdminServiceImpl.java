package com.abc12366.uc.service.admin.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Constant;
import com.abc12366.gateway.util.DateUtils;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.AdminExtendMapper;
import com.abc12366.uc.mapper.db1.AdminMapper;
import com.abc12366.uc.mapper.db1.LoginInfoMapper;
import com.abc12366.uc.mapper.db1.UserRoleMapper;
import com.abc12366.uc.mapper.db2.*;
import com.abc12366.uc.model.admin.*;
import com.abc12366.uc.model.admin.bo.AdminBO;
import com.abc12366.uc.model.admin.bo.AdminUpdateBO;
import com.abc12366.uc.model.admin.bo.LoginInfoBO;
import com.abc12366.uc.model.admin.bo.UserPasswordBO;
import com.abc12366.uc.service.admin.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

/**
 * 操作员服务接口实现类
 *
 * @author lijun <ljun51@outlook.com>
 * @date  2017-06-07 4:02 PM
 * @since 1.0.0
 */
@Service
public class AdminServiceImpl implements AdminService {

    private static Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminRoMapper adminRoMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRoleRoMapper userRoleRoMapper;

    @Autowired
    private MenuRoMapper menuRoMapper;
    @Autowired
    private LoginInfoMapper loginInfoMapper;

    @Autowired
    private LoginInfoRoMapper loginInfoRoMapper;

    @Autowired
    private AdminExtendRoMapper adminExtendRoMapper;

    @Autowired
    private AdminExtendMapper adminExtendMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Admin selectUserByLoginName(String username) {
        return adminRoMapper.selectUserByLoginName(username);
    }

    @Override
    public Admin selectUserById(String id) {
        return adminRoMapper.selectUserById(id);
    }

    @Override
    public List<AdminBO> selectUser(Admin admin) {
        return null;
    }

    @Override
    public List<AdminBO> selectList(AdminBO admin) {
        return adminRoMapper.selectList(admin);
    }

    @Transactional(value = "db1TxManager", rollbackFor = SQLException.class)
    @Override
    public int register(AdminBO adminBO) {
        Admin admin = new Admin();
        try {
            BeanUtils.copyProperties(adminBO, admin);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new ServiceException(4105);
        }
        String password = admin.getPassword();
        admin.setId(Utils.uuid());
        try {
            admin.setPassword(Utils.md5(password));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(4106);
        }
        Date date = new Date();
        admin.setCreateTime(date);
        admin.setLastUpdate(date);

        int ins = adminMapper.insert(admin);

        String id = admin.getId();
        String[] roles = adminBO.getRoleIds().split(",");
        UserRole userRole = new UserRole();

        insertRole(id, roles, userRole);
        return ins;
    }

    /**
     * 插入角色
     * @param id 用户ID
     * @param roles 角色数组
     * @param userRole UserRole
     */
    private void insertRole(String id, String[] roles, UserRole userRole) {
        for (String roleId : roles) {
            userRole.setId(Utils.uuid());
            userRole.setUserId(id);
            userRole.setRoleId(roleId);
            userRoleMapper.insert(userRole);
        }
    }

    @Override
    public void updateUserPwdById(String userId, String pwd) {
        adminMapper.updateUserPwdById(userId, pwd);
    }

    @Override
    public AdminBO selectUserVOById(String id) {
        return adminRoMapper.selectUserBoById(id);
    }

    @Transactional(value = "db1TxManager", rollbackFor = SQLException.class)
    @Override
    public AdminUpdateBO updateUser(AdminUpdateBO adminUpdateBO) {
        AdminUpdateBO uBO = new AdminUpdateBO();
        Admin admin = new Admin();
        try {
            BeanUtils.copyProperties(adminUpdateBO, admin);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new ServiceException(4105);
        }
        Date date = new Date();
        admin.setLastUpdate(date);
        int upd = adminMapper.updateUser(admin);
        if (upd != 1) {
            throw new ServiceException(4102);
        }

        //修改用户详情
        AdminExtend adminExtend = new AdminExtend();
        BeanUtils.copyProperties(adminUpdateBO, adminExtend);
        adminExtend.setUserId(admin.getId());
        adminExtend.setLastUpdate(date);
        int updExtend = adminExtendMapper.updateUserExtentBO(adminExtend);
        if (updExtend != 1) {
            throw new ServiceException(4114);
        }
        BeanUtils.copyProperties(admin, uBO);
        String id = adminUpdateBO.getId();
        List<UserRole> userRoles = userRoleRoMapper.selectUserRoleByUserId(id);
        if (userRoles != null && (!userRoles.isEmpty())) {
            for (UserRole userRole : userRoles) {
                userRoleMapper.deleteById(userRole.getId());
            }
        }

        String[] roles = adminUpdateBO.getRoleIds().split(",");
        UserRole userRole = new UserRole();
        insertRole(id, roles, userRole);
        return uBO;
    }

    @Transactional(value = "db1TxManager", rollbackFor = SQLException.class)
    @Override
    public int deleteUserById(String id) {
        int del = adminMapper.deleteById(id);
        adminExtendMapper.deleteByUserId(id);
        loginInfoMapper.deleteByUserId(id);
        List<UserRole> userRoles = userRoleRoMapper.selectUserRoleByUserId(id);
        if (userRoles != null && (!userRoles.isEmpty())) {
            for (UserRole userRole : userRoles) {
                userRoleMapper.deleteById(userRole.getId());
            }
        }
        return del;
    }

    @Override
    public AdminBO selectOne(String id) {
        return adminRoMapper.selectOne(id);
    }

    @Override
    public AdminBO login(AdminBO adminBO, String appId) {
        AdminBO user = adminRoMapper.selectUserBOByLoginName(adminBO.getUsername());
        //判断用户是否被禁用
        if (user != null) {
            Boolean status = user.getStatus();
            if (!status) {
                LOGGER.error("用户为禁用状态，不能登录{}", user);
                throw new ServiceException(4126);
            }
        }
        String password;
        try {
            password = Utils.md5(adminBO.getPassword());
            String defaultPwd = Utils.md5(Utils.md5(Constant.DEFAULT_PASSWORD));
            if(password.equals(defaultPwd)){
                adminBO.setIsInitPassword(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(4106);
        }
        if (user != null && password.equals(user.getPassword())) {
            String userToken;
            try {
                userToken = Utils.md5(Utils.uuid());
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(4106);
            }
            //查找用户登录信息
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setUserId(user.getId());
            loginInfo.setAppId(appId);
            long lastLong = DateUtils.getDateStringToLong(new Date()) + Constant.ADMIN_TOKEN_VALID_SECONDS;
            loginInfo.setLastResetTokenTime(DateUtils.getLongToDate(lastLong));
            List<LoginInfo> info = loginInfoRoMapper.selectByAppList(loginInfo);

            //判断该用户是否存在此应用的登录信息
            loginInfo.setToken(userToken);
            if (info != null && info.size() > 0) {
                loginInfo.setId(info.get(0).getId());
                int update = loginInfoMapper.update(loginInfo);
                if (update != 1) {
                    LOGGER.error("修改登录信息失败：{}", update);
                    throw new ServiceException(4132);
                }
            } else {
                loginInfo.setId(Utils.uuid());
                int insert = loginInfoMapper.insertSelective(loginInfo);
                if (insert != 1) {
                    LOGGER.error("新增登录信息失败：{}", insert);
                    throw new ServiceException(4131);
                }
            }
            BeanUtils.copyProperties(user, adminBO);
            adminBO.setLoginInfo(loginInfo);

            //查询用户菜单信息
            Map<String, List<Menu>> menuMap = new HashMap<>();
            List<Role> roles = adminBO.getRolesList();
            if(roles != null && roles.size()> 0){
                roles.stream().filter(role -> Objects.equals(Boolean.TRUE, role.getStatus())).filter(role -> role.getId() != null && !"".equals(role.getId())).forEach(role -> {
                    List<Menu> menus = menuRoMapper.selectMenuByRoleId(role.getId());
                    menuMap.put(role.getId(), menus);
                });
            }
            adminBO.setMenuMap(menuMap);
            return adminBO;
        } else {
            throw new ServiceException(4125);
        }
    }

    @Override
    public AdminExtend selectUserExtendByUserId(String id) {
        return adminExtendRoMapper.selectUserExtendByUserId(id);
    }

    @Override
    public boolean isAuthentication(String userToken) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setToken(userToken);
        LoginInfo info = loginInfoRoMapper.selectInfoByToken(loginInfo);
        return info != null;
    }

    @Transactional(value = "db1TxManager", rollbackFor = SQLException.class)
    @Override
    public AdminBO addUser(AdminBO adminBO) {
        AdminBO bo = adminRoMapper.selectUserBOByLoginName(adminBO.getUsername());
        if (bo != null) {
            LOGGER.error("该用户已经存在{}:", adminBO);
            throw new ServiceException(4117);
        }
        Admin admin = new Admin();
        try {
            BeanUtils.copyProperties(adminBO, admin);
        } catch (Exception e) {
            LOGGER.error("类转换异常：{}", e);
            throw new ServiceException(4105);
        }
        String password = admin.getPassword();
        admin.setId(Utils.uuid());
        try {
            admin.setPassword(Utils.md5(password));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(4106);
        }
        Date date = new Date();
        admin.setCreateTime(date);
        admin.setLastUpdate(date);

        int ins = adminMapper.insert(admin);
        if (ins != 1) {
            throw new ServiceException(4101);
        }
        AdminExtend adminExtend = new AdminExtend();
        BeanUtils.copyProperties(adminBO, adminExtend);
        adminExtend.setLastUpdate(date);
        adminExtend.setCreateTime(date);
        adminExtend.setUserId(admin.getId());
        int extInsert = adminExtendMapper.insert(adminExtend);
        if (extInsert != 1) {
            throw new ServiceException(4112);
        }
        String id = admin.getId();
        String[] roles = adminBO.getRoleIds().split(",");
        UserRole userRole = new UserRole();

        int roleIns;
        for (String roleId : roles) {
            userRole.setId(Utils.uuid());
            userRole.setUserId(id);
            userRole.setRoleId(roleId);
            roleIns = userRoleMapper.insert(userRole);
            if (roleIns != 1) {
                throw new ServiceException(4113);
            }
        }
        AdminBO temp = new AdminBO();
        BeanUtils.copyProperties(admin, temp);
        return temp;
    }

    @Transactional(value = "db1TxManager", rollbackFor = SQLException.class)
    @Override
    public void logout(String token) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setToken(token);
        //退出时，重置token
        LoginInfo temp = loginInfoRoMapper.selectInfoByToken(loginInfo);
        if (temp != null) {
            // delete app token in redis
            redisTemplate.delete(token);
            try {
                String newToken = Utils.md5(Utils.uuid());
                temp.setToken(newToken);
                loginInfoMapper.update(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public int updateUserPwd(UserPasswordBO userPasswordBO) {
        int update;
        AdminBO user = adminRoMapper.selectUserBoById(userPasswordBO.getId());
        String newPassword;
        String oldPassword;
        try {
            newPassword = Utils.md5(userPasswordBO.getNewPassword());
            oldPassword = Utils.md5(userPasswordBO.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(4106);
        }
        if (user != null && oldPassword.equals(user.getPassword())) {
            update = adminMapper.updateUserPwdById(user.getId(), newPassword);
        } else {
            throw new ServiceException(4120);
        }

        return update;
    }

    @Override
    public int resetUserPwd(String id) {
        String newPassword;
        try {
            newPassword = Utils.md5(Utils.md5(Constant.DEFAULT_PASSWORD));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(4106);
        }
        int update = adminMapper.updateUserPwdById(id, newPassword);
        if (update != 1) {
            throw new ServiceException(4102);
        }
        return update;
    }

    @Transactional(value = "db1TxManager", rollbackFor = SQLException.class)
    @Override
    public void enable(AdminUpdateBO adminUpdateBO) {
        String[] idArray = adminUpdateBO.getId().split(",");
        Admin admin = new Admin();
        admin.setLastUpdate(new Date());
        for (String userId : idArray) {
            admin.setId(userId);
            admin.setStatus(adminUpdateBO.getStatus());
            int update = adminMapper.updateUser(admin);
            if (update != 1) {
                LOGGER.warn("修改失败，id：{}", admin.toString());
                throw new ServiceException(4102);
            }
        }
    }

    @Override
    public Boolean checkToken(String token) {

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setToken(token);
        //验证Token是否存在
        LoginInfo info = loginInfoRoMapper.selectInfoByToken(loginInfo);
        if (info == null) {
            LOGGER.warn("Admin-Token不存在{}", loginInfo);
            throw new ServiceException(4128);
        }
        //严重Token是否过期
        long datelong = System.currentTimeMillis();
        long lastTime = DateUtils.getDateStringToLong(info.getLastResetTokenTime());
        if (datelong > lastTime) {
            LOGGER.warn("Admin-Token过期，请重新登录{}", info);
            throw new ServiceException(4127);
        }
        return true;
    }

    @Override
    public Boolean refreshToken(String token) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setToken(token);
        //验证Token是否存在
        LoginInfo info = loginInfoRoMapper.selectInfoByToken(loginInfo);
        if (info == null) {
            LOGGER.warn("Admin-Token不存在{}", token);
            throw new ServiceException(4128);
        }
        long datelong = System.currentTimeMillis() + Constant.ADMIN_TOKEN_VALID_SECONDS;
        info.setLastResetTokenTime(DateUtils.getLongToDate(datelong));
        int upd = loginInfoMapper.update(info);
        if (upd != 1) {
            LOGGER.warn("修改Admin-token有效时间失败{}", info);
            throw new ServiceException(4129);
        }
        return true;
    }

    @Override
    public LoginInfoBO selectLoginInfoByToken(String token) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setToken(token);
        //验证Token是否存在
        LoginInfo info = loginInfoMapper.selectInfoByToken(loginInfo);
        if (info == null) {
            LOGGER.warn("Admin-Token不存在{}", loginInfo);
            throw new ServiceException(4128);
        }
        //严重Token是否过期
        long dateLong = System.currentTimeMillis();
        long lastTime = DateUtils.getDateStringToLong(info.getLastResetTokenTime());
        if (dateLong > lastTime) {
            LOGGER.warn("Admin-Token过期，请重新登录{}", info);
            throw new ServiceException(4127);
        }

        long refLong = dateLong + Constant.ADMIN_TOKEN_VALID_SECONDS;
        info.setLastResetTokenTime(DateUtils.getLongToDate(refLong));
        int upd = loginInfoMapper.update(info);
        if (upd != 1) {
            LOGGER.warn("修改Admin-token有效时间失败{}", info);
            throw new ServiceException(4129);
        }
        return loginInfoMapper.selectLoginInfoByToken(token);
    }

    @Transactional(value = "db1TxManager", rollbackFor = SQLException.class)
    @Override
    public void disableAll() {
        AdminBO adminBO = new AdminBO();
        List<AdminBO> adminBOs = adminRoMapper.selectList(adminBO);
        for (AdminBO temp : adminBOs) {
            Admin admin = new Admin();
            admin.setId(temp.getId());
            admin.setStatus(false);
            int enable = adminMapper.updateUser(admin);
            if (enable != 1) {
                LOGGER.warn("修改失败，id：{}", admin.toString());
                throw new ServiceException(4102);
            }
        }
    }
}
