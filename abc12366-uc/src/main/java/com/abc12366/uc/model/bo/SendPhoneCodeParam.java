package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * 发送手机验证码短信入参
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-14
 * Time: 15:18
 */
public class SendPhoneCodeParam {
    //手机号码
    @NotEmpty(message = "手机号码不能为空")
    @Pattern(regexp = "^\\d{11}$", message = "手机号码格式不正确")
    private String phone;
    //验证码类型
    @NotEmpty(message = "验证码类型不能为空")
    private String type;

    public SendPhoneCodeParam() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SendPhoneCodeParam{" +
                "phone='" + phone + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
