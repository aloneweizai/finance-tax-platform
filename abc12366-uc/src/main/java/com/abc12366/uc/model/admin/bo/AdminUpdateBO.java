package com.abc12366.uc.model.admin.bo;

import com.abc12366.uc.model.admin.LoginInfo;
import com.abc12366.uc.model.admin.Menu;
import com.abc12366.uc.model.admin.Role;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 系统用户表
 **/
@SuppressWarnings("serial")
public class AdminUpdateBO extends AdminExtendUpdateBO implements Serializable {

    private String id;
    private String username;
    private String password;
    private String nickname;
    private Boolean status;
    private String roleIds;
    private List<Role> rolesList;
    private LoginInfo loginInfo;
    private Map<String, List<Menu>> menuMap;

    public List<Role> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Role> rolesList) {
        this.rolesList = rolesList;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public Map<String, List<Menu>> getMenuMap() {
        return menuMap;
    }

    public void setMenuMap(Map<String, List<Menu>> menuMap) {
        this.menuMap = menuMap;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AdminUpdateBO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", status=" + status +
                ", roleIds='" + roleIds + '\'' +
                ", rolesList=" + rolesList +
                ", loginInfo=" + loginInfo +
                ", menuMap=" + menuMap +
                "} " + super.toString();
    }
}
