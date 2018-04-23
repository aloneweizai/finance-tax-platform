package com.abc12366.uc.service.admin.impl;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;
import com.abc12366.uc.mapper.db1.RoleMapper;
import com.abc12366.uc.mapper.db1.RoleMenuMapper;
import com.abc12366.uc.mapper.db1.UserRoleMapper;
import com.abc12366.uc.mapper.db2.RoleRoMapper;
import com.abc12366.uc.mapper.db2.UserRoleRoMapper;
import com.abc12366.uc.model.admin.Role;
import com.abc12366.uc.model.admin.RoleMenu;
import com.abc12366.uc.model.admin.UserRole;
import com.abc12366.uc.model.admin.bo.RoleBO;
import com.abc12366.uc.model.admin.bo.RoleUpdateBO;
import com.abc12366.uc.model.admin.bo.UserRoleBO;
import com.abc12366.uc.service.admin.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleRoMapper roleRoMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserRoleRoMapper userRoleRoMapper;


    @Override
    public List<RoleBO> selectList(Role role) {
        return roleRoMapper.selectList(role);
    }

    @Transactional("db1TxManager")
    @Override
    public Role addRole(RoleBO roleBO) {
        Role role = new Role();

        role.setRoleName(roleBO.getRoleName());
        Role temp = roleRoMapper.selectRoleByName(role);
        if (temp != null) {
            logger.warn("角色名称已存在，参数：{}", role.toString());
            throw new ServiceException(4111);
        }
        BeanUtils.copyProperties(roleBO, role);
        role.setId(Utils.uuid());
        Date date = new Date();
        role.setCreateTime(date);
        role.setUpdateTime(date);
        role.setStatus(true);
        int insert = roleMapper.insert(role);
        if (insert != 1) {
            logger.warn("插入失败，参数：{}", role.toString());
            throw new ServiceException(4101);
        }
        String roleId = role.getId();
        String[] resources = roleBO.getMenuIds().split(",");
        RoleMenu roleMenu = new RoleMenu();
        for (String menuId : resources) {
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenu.setId(Utils.uuid());
            roleMenuMapper.insert(roleMenu);
        }
        return role;
    }

    @Transactional("db1TxManager")
    @Override
    public int deleteRoleById(String id) {
        int del = roleMapper.deleteRoleById(id);
        if (del != 1) {
            logger.warn("删除失败，id：{}", id);
            throw new ServiceException(4103);
        }
        //删除用户和角色对应关系
        userRoleMapper.deleteByRoleId(id);
        //删除角色和权限对应关系
        roleMenuMapper.deleteByRoleId(id);
        return del;
    }

    @Override
    public RoleBO selectRoleById(String id) {
        return roleRoMapper.selectRoleById(id);
    }

    @Transactional("db1TxManager")
    @Override
    public RoleBO updateRole(RoleBO roleBO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleBO, role);
        role.setUpdateTime(new Date());
        int update = roleMapper.updateRole(role);
        if (update != 1) {
            logger.warn("更新失败，参数：{}", roleBO.toString());
            throw new ServiceException(4102);
        }

        String roleId = role.getId();
        List<String> roleMenuIdList = roleRoMapper.selectRoleMenuIdListByRoleId(roleId);
        if (roleMenuIdList != null && (!roleMenuIdList.isEmpty())) {
            roleMenuIdList.stream().filter(roleMenuId -> roleMenuId != null).forEach(roleMenuMapper::deleteById);
        }
        String[] resources = roleBO.getMenuIds().split(",");
        RoleMenu roleMenu = new RoleMenu();
        for (String menuId : resources) {
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenu.setId(Utils.uuid());
            roleMenuMapper.insert(roleMenu);
        }
        RoleBO bo = new RoleBO();
        BeanUtils.copyProperties(role, bo);
        bo.setMenuIds(roleBO.getMenuIds());
        return bo;
    }

    @Override
    public List<String> selectMenuIdListByRoleId(String id) {
        return roleRoMapper.selectMenuIdListByRoleId(id);
    }

    @Transactional("db1TxManager")
    @Override
    public void updateRoleMenu(String id, String menuIds) {
        List<String> roleMenuIdList = roleRoMapper.selectRoleMenuIdListByRoleId(id);
        if (roleMenuIdList != null && (!roleMenuIdList.isEmpty())) {
            roleMenuIdList.stream().filter(roleMenuId -> roleMenuId != null).forEach(roleMenuMapper::deleteById);
        }
        String[] resources = menuIds.split(",");
        RoleMenu roleMenu = new RoleMenu();
        for (String menuId : resources) {
            roleMenu.setRoleId(id);
            roleMenu.setMenuId(menuId);
            roleMenu.setId(Utils.uuid());
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public List<String> selectRoleIdListByUserId(String userId) {
        return userRoleRoMapper.selectRoleIdListByUserId(userId);
    }

    @Override
    public List<Map<String, String>> selectRoleResourceListByRoleId(String roleId) {
        return roleRoMapper.selectRoleResourceListByRoleId(roleId);
    }

    @Override
    public Role selectOne(RoleBO roleBO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleBO, role);
        return roleRoMapper.selectOne(role);
    }

    @Override
    public Role selectRoleByName(RoleBO roleBO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleBO, role);
        return roleRoMapper.selectRoleByName(role);
    }

    @Override
    public RoleBO selectUserByRoleId(String id) {
        return roleRoMapper.selectUserByRoleId(id);
    }

    @Transactional("db1TxManager")
    @Override
    public UserRoleBO updateUserRole(UserRoleBO userRoleBO) {
        UserRoleBO bo = new UserRoleBO();
        String roleId = userRoleBO.getRoleId();
        if (roleId == null || "".equals(roleId)) {
            Role role = userRoleBO.getRole();
            role.setRoleName(role.getRoleName());
            Role temp = roleRoMapper.selectRoleByName(role);
            if (temp != null) {
                logger.warn("角色名称已存在，参数：{}", role.toString());
                throw new ServiceException(4111);
            }
            roleId = Utils.uuid();
            role.setId(roleId);
            Date date = new Date();
            role.setCreateTime(date);
            role.setRemark(role.getRemark());
            role.setStatus(true);
            int ins = roleMapper.insert(role);
            if (ins != 1) {
                logger.warn("新增用户角色异常，参数：{}", role);
                throw new ServiceException(4113);
            }
            bo.setRoleId(roleId);
            bo.setRole(role);
        }
        List<UserRole> roleMenuIdList = userRoleRoMapper.selectUserRoleByRoleId(roleId);
        if (roleMenuIdList != null && (!roleMenuIdList.isEmpty())) {
            userRoleMapper.deleteByRoleId(roleId);
        }
        String[] resources = userRoleBO.getUserId().split(",");
        UserRole roleMenu = new UserRole();
        for (String userId : resources) {
            roleMenu.setRoleId(roleId);
            roleMenu.setUserId(userId);
            roleMenu.setId(Utils.uuid());
            int urInsert = userRoleMapper.insert(roleMenu);
            if (urInsert != 1) {
                logger.warn("新增用户角色和菜单对应关系异常，参数：{}", roleMenu);
                throw new ServiceException(4096);
            }
        }
        return bo;
    }

    @Transactional("db1TxManager")
    @Override
    public void enable(RoleUpdateBO roleUpdateBO) {
        Role role = new Role();
        role.setId(roleUpdateBO.getId());
        role.setStatus(roleUpdateBO.getStatus());
        int update = roleMapper.updateRole(role);
        if(update != 1){
            logger.warn("修改失败参数：{}", role);
            throw new ServiceException(4101);
        }
    }
}
