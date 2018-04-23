package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 手机验证码校验参数实体类
 *
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-01
 * Time: 10:40
 */
public class VerifyingCodeBO implements Serializable {
    //验证码类型
    @NotEmpty
    private String type;
    //手机号码
    @NotEmpty
    @Size(min = 11, max = 11)
    private String phone;
    //验证码
    @NotEmpty
    @Size(min = 6, max = 6)
    private String code;

    public VerifyingCodeBO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "VerifyingCodeBO{" +
                "type='" + type + '\'' +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
