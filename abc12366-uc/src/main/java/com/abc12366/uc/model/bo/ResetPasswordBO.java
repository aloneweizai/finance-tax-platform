package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户通过手机号重置密码
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-11-09 4:58 PM
 * @since 1.0.0
 */
public class ResetPasswordBO {

    @NotEmpty
    @Length(min = 11, max = 11)
    private String phone;

    @NotEmpty
    private String password;

    @NotEmpty
    @Length(min = 16, max = 64)
    private String token;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ResetPasswordBO{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
