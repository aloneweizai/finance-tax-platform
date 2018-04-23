package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2018-01-05
 * Time: 11:14
 */
public class UserResetPwdBO {
    @NotEmpty(message = "用户ID不能为空")
    private String userId;
    @NotEmpty(message = "原因不能为空")
    private String reason;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "UserResetPwdBO{" +
                "userId='" + userId + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
