package com.abc12366.uc.model.admin.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 系统用户表
 **/
@SuppressWarnings("serial")
public class UserPasswordBO extends AdminExtendBO implements Serializable {

    /**
     * ID
     **/
    private String id;

    /**
     * 用户名
     **/
    private String username;

    /**
     * 密码
     **/
    @NotEmpty
    @Size(min = 8, max = 32)
    private String password;

    @NotEmpty
    @Size(min = 8, max = 32)
    /**新密码**/
    private String newPassword;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
