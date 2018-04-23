package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS栏目用户关联表
 **/
@SuppressWarnings("serial")
public class ChannelUser implements Serializable {

    /**
     * channelId**varchar(64)
     **/
    private String channelId;

    /**
     * userId**varchar(64)
     **/
    private String userId;

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
