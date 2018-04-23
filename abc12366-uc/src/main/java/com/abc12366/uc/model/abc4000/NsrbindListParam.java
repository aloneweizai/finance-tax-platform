package com.abc12366.uc.model.abc4000;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-10
 * Time: 11:48
 */
public class NsrbindListParam {
    //用户ID
    @NotEmpty
    private String userId;

    public NsrbindListParam() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
