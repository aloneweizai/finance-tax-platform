package com.abc12366.cms.model.questionnaire;

import java.io.Serializable;


/**
 * 背景图片表
 **/
@SuppressWarnings("serial")
public class Images implements Serializable {

    /****/
    private String id;

    /**
     * 名称
     **/
    private String name;

    /**
     * 图片名称
     **/
    private String imgName;

    /**
     * 图片URL
     **/
    private String imgUrl;

    /**
     * 图片真实URL
     **/
    private String imgRealUrl;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgName() {
        return this.imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgRealUrl() {
        return this.imgRealUrl;
    }

    public void setImgRealUrl(String imgRealUrl) {
        this.imgRealUrl = imgRealUrl;
    }

}
