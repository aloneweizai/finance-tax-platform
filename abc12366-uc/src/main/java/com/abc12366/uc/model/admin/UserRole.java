package com.abc12366.uc.model.admin;

import java.io.Serializable;


/**
 * 系统用户角色关联表
 **/
@SuppressWarnings("serial")
public class UserRole implements Serializable {

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

}
