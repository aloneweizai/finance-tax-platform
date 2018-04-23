package com.abc12366.uc.model.admin.bo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 系统角色表
 **/
@SuppressWarnings("serial")
public class RoleUpdateBO implements Serializable {

    private String id;

    @NotNull
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
