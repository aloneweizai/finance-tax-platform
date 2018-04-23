package com.abc12366.uc.model.bo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-03-28 4:34 PM
 * @since 1.0.0
 */
public class UserPhoneBO {

    private String id;
    @Pattern(regexp = "^\\d{11}$")
    @Size(min = 11, max = 11)
    private String phone;
    private String reason;
    @Size(max = 20)
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserPhoneBO{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", reason='" + reason + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
