package com.abc12366.bangbang.model.bo;

import javax.validation.constraints.Size;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-29
 * Time: 14:35
 */
public class TeamMemberUpdateBO {
    @Size(max = 1)
    private String type;
    private String createUser;
    @Size(max = 1)
    private String status;

    public TeamMemberUpdateBO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
