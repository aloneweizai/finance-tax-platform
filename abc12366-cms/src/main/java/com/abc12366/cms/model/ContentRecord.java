package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * 文章操作记录
 **/
@SuppressWarnings("serial")
public class ContentRecord implements Serializable {

    /**
     * contentRecordId**varchar(64)
     **/
    private String contentRecordId;

    /**
     * 文章ID**varchar(64)
     **/
    private String contentId;

    /**
     * 操作人**varchar(64)
     **/
    private String userId;

    /**
     * 操作时间**datetime
     **/
    private java.util.Date operateTime;

    /**
     * 0 新增 1修改 2审核 3退回 4移动 5生成静态页 6删除到回收站 7归档 8出档 9推送共享**tinyint(2)
     **/
    private Integer operateType;

    public String getContentRecordId() {
        return this.contentRecordId;
    }

    public void setContentRecordId(String contentRecordId) {
        this.contentRecordId = contentRecordId;
    }

    public String getContentId() {
        return this.contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public java.util.Date getOperateTime() {
        return this.operateTime;
    }

    public void setOperateTime(java.util.Date operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getOperateType() {
        return this.operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

}
