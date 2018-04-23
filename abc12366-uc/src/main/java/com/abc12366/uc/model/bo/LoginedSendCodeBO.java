package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户登录后需要发送短信参数bo
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-12
 * Time: 15:00
 */
public class LoginedSendCodeBO {
    //用户ID
    @NotEmpty
    private String userId;
    //短信类型
    @NotEmpty
    private String type;

    public LoginedSendCodeBO() {
    }

    public LoginedSendCodeBO(String userId, String type) {
        this.userId = userId;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LoginedSendCodeBO{" +
                "userId='" + userId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
