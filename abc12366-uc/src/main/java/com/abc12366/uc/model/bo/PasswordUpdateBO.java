package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-19
 * Time: 10:15
 */
public class PasswordUpdateBO {

    @NotEmpty
    private String password;

    public PasswordUpdateBO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PasswordUpdateBO{" +
                ", password='" + password + '\'' +
                '}';
    }
}
