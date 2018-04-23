package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS友情链接类别
 **/
@SuppressWarnings("serial")
public class FriendlinkCtg implements Serializable {

    /**
     * friendlinkctgId**varchar(64)
     **/
    private String friendlinkctgId;

    /**
     * siteId**varchar(64)
     **/
    private String siteId;

    /**
     * 名称**varchar(50)
     **/
    private String friendlinkctgName;

    /**
     * 排列顺序**int(11)
     **/
    private Integer priority;

    public String getFriendlinkctgId() {
        return this.friendlinkctgId;
    }

    public void setFriendlinkctgId(String friendlinkctgId) {
        this.friendlinkctgId = friendlinkctgId;
    }

    public String getSiteId() {
        return this.siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getFriendlinkctgName() {
        return this.friendlinkctgName;
    }

    public void setFriendlinkctgName(String friendlinkctgName) {
        this.friendlinkctgName = friendlinkctgName;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

}
