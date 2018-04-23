package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS内容栏目关联表
 **/
@SuppressWarnings("serial")
public class ContentChannel implements Serializable {

    /**
     * channelId**varchar(64)
     **/
    private String channelId;

    /**
     * contentId**varchar(64)
     **/
    private String contentId;

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getContentId() {
        return this.contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

}
