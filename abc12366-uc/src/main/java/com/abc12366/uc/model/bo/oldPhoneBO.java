package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * 旧手机校验实体类
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-14
 * Time: 17:14
 */
public class oldPhoneBO {
    //旧手机号码
    @NotEmpty(message = "旧手机号码不能为空")
    @Pattern(regexp = "^\\d{11}$", message = "手机号码格式不正确")
    private String oldPhone;

    public oldPhoneBO() {
    }

    public String getOldPhone() {
        return oldPhone;
    }

    public void setOldPhone(String oldPhone) {
        this.oldPhone = oldPhone;
    }

    @Override
    public String toString() {
        return "oldPhoneBO{" +
                "oldPhone='" + oldPhone + '\'' +
                '}';
    }
}
