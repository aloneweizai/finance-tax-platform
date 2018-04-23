package com.abc12366.message.model;

/**
 * msg_uc_user视图查询返回参数实体类
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-23
 * Time: 16:15
 */
public class PhoneExist {
    private String username;
    private String phone;

    public PhoneExist() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "PhoneExist{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
