package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS内容图片表
 **/
@SuppressWarnings("serial")
public class ContentPicture implements Serializable {

    /**
     * contentId**varchar(64)
     **/
    private String contentId;

    /**
     * 排列顺序**int(11)
     **/
    private Integer priority;

    /**
     * 图片地址**varchar(100)
     **/
    private String imgPath;

    /**
     * 描述**varchar(255)
     **/
    private String description;

    public String getContentId() {
        return this.contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getImgPath() {
        return this.imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
