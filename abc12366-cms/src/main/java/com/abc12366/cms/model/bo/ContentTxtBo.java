package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * CMS内容文本表
 * add by xieyanmao on 2017-4-25
 **/
@SuppressWarnings("serial")
public class ContentTxtBo implements Serializable {

    /**
     * contentId**varchar(64)
     **/
    private String contentId;

    /**
     * 文章内容**longtext
     **/
    private String txt;

    public String getContentId() {
        return this.contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getTxt() {
        return this.txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

}
