package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * 附件表
 * add by xieyanmao on 2017-4-25
 **/
@SuppressWarnings("serial")
public class FileBo implements Serializable {

    /**
     * 文件路径**varchar(255)
     **/
    private String filePath;

    /**
     * 文件名字**varchar(255)
     **/
    private String fileName;

    /**
     * 是否有效**tinyint(1)
     **/
    private Integer fileIsvalid;

    /**
     * 内容id**varchar(64)
     **/
    private String contentId;

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileIsvalid() {
        return this.fileIsvalid;
    }

    public void setFileIsvalid(Integer fileIsvalid) {
        this.fileIsvalid = fileIsvalid;
    }

    public String getContentId() {
        return this.contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

}
