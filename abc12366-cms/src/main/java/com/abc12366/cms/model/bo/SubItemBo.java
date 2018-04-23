package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * 更新状态
 **/
@SuppressWarnings("serial")
public class SubItemBo implements Serializable {
    private String id;

    private String bz;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
