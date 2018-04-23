package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS内容文本表
 **/
@SuppressWarnings("serial")
public class ContentTxt implements Serializable {

    /**
     * contentId**varchar(64)
     **/
    private String contentId;

    /**
     * 文章内容**longtext
     **/
    private String txt;

    /**
     * 扩展内容1**longtext
     **/
    private String txt1;

    /**
     * 扩展内容2**longtext
     **/
    private String txt2;

    /**
     * 扩展内容3**longtext
     **/
    private String txt3;

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

    public String getTxt1() {
        return this.txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public String getTxt2() {
        return this.txt2;
    }

    public void setTxt2(String txt2) {
        this.txt2 = txt2;
    }

    public String getTxt3() {
        return this.txt3;
    }

    public void setTxt3(String txt3) {
        this.txt3 = txt3;
    }

}
