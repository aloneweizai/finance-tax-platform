package com.abc12366.uc.model.admin.bo;

import com.abc12366.uc.model.admin.Role;

import java.io.Serializable;


/**
 * 系统用户角色关联表
 **/
@SuppressWarnings("serial")
public class UserRoleBO implements Serializable {

    /**
     * ID
     **/
    private String id;

    /**
     * 用户ID
     **/
    private String userId;

    /**
     * 角色ID
     **/
    private String roleId;

    private Role role;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
