package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * CMS内容浏览会员组关联表
 **/
@SuppressWarnings("serial")
public class ContentGroupViewBo implements Serializable {

    /****
     * v(64)
     **/
    private String contentId;

    /****
     * v(64)
     **/
    private String groupId;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
