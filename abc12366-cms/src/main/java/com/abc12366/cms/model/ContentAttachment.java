package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS内容附件表
 **/
@SuppressWarnings("serial")
public class ContentAttachment implements Serializable {

    /**
     * contentId**varchar(64)
     **/
    private String contentId;

    /**
     * 排列顺序**int(11)
     **/
    private Integer priority;

    /**
     * 附件路径**varchar(255)
     **/
    private String attachmentPath;

    /**
     * 附件名称**varchar(100)
     **/
    private String attachmentName;

    /**
     * 文件名**varchar(100)
     **/
    private String filename;

    /**
     * 下载次数**int(11)
     **/
    private Integer downloadCount;

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

    public String getAttachmentPath() {
        return this.attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getAttachmentName() {
        return this.attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getDownloadCount() {
        return this.downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

}
