package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS栏目浏览会员组关联表
 **/
@SuppressWarnings("serial")
public class ChnlGroupView implements Serializable {

    /****
     * v(64)
     **/
    private String channelId;

    /****
     * v(64)
     **/
    private String groupId;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
