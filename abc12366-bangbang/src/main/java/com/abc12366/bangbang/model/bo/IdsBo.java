package com.abc12366.bangbang.model.bo;

import java.io.Serializable;


/**
 * 文件上传
 **/
@SuppressWarnings("serial")
public class IdsBo implements Serializable {
    private String[] ids;

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
