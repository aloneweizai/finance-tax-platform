package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS内容标签关联表
 **/
@SuppressWarnings("serial")
public class Contenttagid implements Serializable {

    /**
     * contentId**varchar(64)
     **/
    private String contentId;

    /**
     * tagId**varchar(64)
     **/
    private String tagId;

    private String tagName;

    /**
     * priority**int(11)
     **/
    private Integer priority;

    public String getContentId() {
        return this.contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getTagId() {
        return this.tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }


    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

}
