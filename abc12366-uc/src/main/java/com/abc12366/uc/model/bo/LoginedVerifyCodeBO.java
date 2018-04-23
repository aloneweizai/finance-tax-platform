package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户通过用户ID发送短信并校验短信验证码实体类
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-14
 * Time: 16:03
 */
public class LoginedVerifyCodeBO {
    //用户ID
    @NotEmpty(message = "用户ID不能为空")
    private String userId;
    //短信类型
    @NotEmpty(message = "验证码类型不能为空")
    private String type;
    //验证码
    @NotEmpty(message = "验证码不能为空")
    private String code;

    public LoginedVerifyCodeBO() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "LoginedVerifyCodeBO{" +
                "userId='" + userId + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
