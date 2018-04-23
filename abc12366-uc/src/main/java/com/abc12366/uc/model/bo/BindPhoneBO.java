package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户绑定手机入参实体类
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-13
 * Time: 10:28
 */
public class BindPhoneBO {
    //用户ID
    @NotEmpty(message = "用户ID不能为空")
    private String userId;
    //手机号码
    private String oldPhone;
    //新手机号码
    @NotEmpty(message = "手机号码不能为空")
    private String newPhone;
    //验证码
    @NotEmpty(message = "验证码不能为空")
    private String code;
    //验证码类型
    @NotEmpty(message = "验证码类型不能为空")
    private String type;

    public BindPhoneBO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOldPhone() {
        return oldPhone;
    }

    public void setOldPhone(String oldPhone) {
        this.oldPhone = oldPhone;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BindPhoneBO{" +
                "userId='" + userId + '\'' +
                ", oldPhone='" + oldPhone + '\'' +
                ", newPhone='" + newPhone + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
