package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * CMS内容图片表
 * add by xieyanmao on 2017-4-25
 **/
@SuppressWarnings("serial")
public class ContentPictureBo implements Serializable {

    /****/
    private String contentId;

    /**
     * 排列顺序
     **/
    private Integer priority;

    /**
     * 图片地址
     **/
    private String imgPath;

    /**
     * 描述
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
