package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS栏目内容表
 **/
@SuppressWarnings("serial")
public class ChannelExt implements Serializable {

    /**
     * channelId**varchar(64)
     **/
    private String channelId;

    /**
     * 名称**varchar(100)
     **/
    private String channelName;

    /**
     * 是否栏目静态化**char(1)
     **/
    private String isStaticChannel;

    /**
     * 是否内容静态化**char(1)
     **/
    private String isStaticContent;

    /**
     * 每页多少条记录**int(11)
     **/
    private Integer pageSize;

    /**
     * 外部链接**varchar(255)
     **/
    private String link;

    /**
     * 栏目页模板**varchar(100)
     **/
    private String tplChannel;

    /**
     * 内容页模板**varchar(100)
     **/
    private String tplContent;

    /**
     * 缩略图**varchar(100)
     **/
    private String titleImg;

    /**
     * 内容图**varchar(100)
     **/
    private String contentImg;

    /**
     * 内容是否有缩略图**tinyint(1)
     **/
    private Integer hasTitleImg;

    /**
     * 内容是否有内容图**tinyint(1)
     **/
    private Integer hasContentImg;

    /**
     * 内容标题图宽度**int(11)
     **/
    private Integer titleImgWidth;

    /**
     * 内容标题图高度**int(11)
     **/
    private Integer titleImgHeight;

    /**
     * 内容内容图宽度**int(11)
     **/
    private Integer contentImgWidth;

    /**
     * 内容内容图高度**int(11)
     **/
    private Integer contentImgHeight;

    /**
     * 评论(0:匿名;1:会员一次;2:关闭,3会员多次)**int(11)
     **/
    private Integer commentControl;

    /**
     * 顶踩(true:开放;false:关闭)**tinyint(1)
     **/
    private Integer allowUpdown;

    /**
     * 是否新窗口打开**tinyint(1)
     **/
    private Integer isBlank;

    /**
     * 分享(true:开放;false:关闭)**tinyint(1)
     **/
    private Integer allowShare;

    /**
     * title**varchar(255)
     **/
    private String title;

    /**
     * keywords**varchar(255)
     **/
    private String keywords;

    /**
     * description**varchar(255)
     **/
    private String description;

    /**
     * 内容**longtext
     **/
    private String txt;

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getIsStaticChannel() {
        return this.isStaticChannel;
    }

    public void setIsStaticChannel(String isStaticChannel) {
        this.isStaticChannel = isStaticChannel;
    }

    public String getIsStaticContent() {
        return this.isStaticContent;
    }

    public void setIsStaticContent(String isStaticContent) {
        this.isStaticContent = isStaticContent;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTplChannel() {
        return this.tplChannel;
    }

    public void setTplChannel(String tplChannel) {
        this.tplChannel = tplChannel;
    }

    public String getTplContent() {
        return this.tplContent;
    }

    public void setTplContent(String tplContent) {
        this.tplContent = tplContent;
    }

    public String getTitleImg() {
        return this.titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getContentImg() {
        return this.contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }

    public Integer getHasTitleImg() {
        return this.hasTitleImg;
    }

    public void setHasTitleImg(Integer hasTitleImg) {
        this.hasTitleImg = hasTitleImg;
    }

    public Integer getHasContentImg() {
        return this.hasContentImg;
    }

    public void setHasContentImg(Integer hasContentImg) {
        this.hasContentImg = hasContentImg;
    }

    public Integer getTitleImgWidth() {
        return this.titleImgWidth;
    }

    public void setTitleImgWidth(Integer titleImgWidth) {
        this.titleImgWidth = titleImgWidth;
    }

    public Integer getTitleImgHeight() {
        return this.titleImgHeight;
    }

    public void setTitleImgHeight(Integer titleImgHeight) {
        this.titleImgHeight = titleImgHeight;
    }

    public Integer getContentImgWidth() {
        return this.contentImgWidth;
    }

    public void setContentImgWidth(Integer contentImgWidth) {
        this.contentImgWidth = contentImgWidth;
    }

    public Integer getContentImgHeight() {
        return this.contentImgHeight;
    }

    public void setContentImgHeight(Integer contentImgHeight) {
        this.contentImgHeight = contentImgHeight;
    }

    public Integer getCommentControl() {
        return this.commentControl;
    }

    public void setCommentControl(Integer commentControl) {
        this.commentControl = commentControl;
    }

    public Integer getAllowUpdown() {
        return this.allowUpdown;
    }

    public void setAllowUpdown(Integer allowUpdown) {
        this.allowUpdown = allowUpdown;
    }

    public Integer getIsBlank() {
        return this.isBlank;
    }

    public void setIsBlank(Integer isBlank) {
        this.isBlank = isBlank;
    }

    public Integer getAllowShare() {
        return this.allowShare;
    }

    public void setAllowShare(Integer allowShare) {
        this.allowShare = allowShare;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
