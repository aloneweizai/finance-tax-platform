package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * 文件上传
 **/
@SuppressWarnings("serial")
public class IdsBo implements Serializable {
    private String[] ids;

    //活动审核拒绝理由
    private String text;

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
