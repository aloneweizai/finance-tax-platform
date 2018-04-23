package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS专题内容关联表
 **/
@SuppressWarnings("serial")
public class ContentTopic implements Serializable {

    /**
     * contentId**varchar(64)
     **/
    private String contentId;

    /**
     * topicId**varchar(64)
     **/
    private String topicId;

    public String getContentId() {
        return this.contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getTopicId() {
        return this.topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

}
