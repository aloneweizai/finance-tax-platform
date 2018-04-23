package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-26
 * Time: 17:05
 */
public class UserHndsInsertBO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String subuser;
    @NotEmpty
    private String password;

    public UserHndsInsertBO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubuser() {
        return subuser;
    }

    public void setSubuser(String subuser) {
        this.subuser = subuser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserHndsInsertBO{" +
                "username='" + username + '\'' +
                ", subuser='" + subuser + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
