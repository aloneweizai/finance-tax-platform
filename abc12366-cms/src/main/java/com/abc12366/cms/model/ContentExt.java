package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS内容扩展表
 **/
@SuppressWarnings("serial")
public class ContentExt implements Serializable {

    /**
     * contentId**varchar(64)
     **/
    private String contentId;

    /**
     * 标题**varchar(150)
     **/
    private String title;

    /**
     * 简短标题**varchar(150)
     **/
    private String shortTitle;

    /**
     * 作者**varchar(100)
     **/
    private String author;

    /**
     * 来源**varchar(100)
     **/
    private String origin;

    /**
     * 来源链接**varchar(255)
     **/
    private String originUrl;

    /**
     * 描述**varchar(255)
     **/
    private String description;

    /**
     * 发布日期**datetime
     **/
    private java.util.Date releaseDate;

    /**
     * 媒体路径**varchar(255)
     **/
    private String mediaPath;

    /**
     * 媒体类型**varchar(20)
     **/
    private String mediaType;

    /**
     * 是否加粗**tinyint(1)
     **/
    private Integer isBold;

    /**
     * 标题颜色**varchar(10)
     **/
    private String titleColor;

    /**
     * 标题图片**varchar(100)
     **/
    private String titleImg;

    /**
     * 内容图片**varchar(100)
     **/
    private String contentImg;

    /**
     * 类型图片**varchar(100)
     **/
    private String typeImg;

    /**
     * 外部链接**varchar(255)
     **/
    private String link;

    /**
     * 指定模板**varchar(100)
     **/
    private String tplContent;

    /**
     * 需要重新生成静态页**tinyint(1)
     **/
    private Integer needRegenerate;

    /**
     * 固顶到期日期**datetime
     **/
    private java.util.Date toplevelDate;

    /**
     * 归档日期**datetime
     **/
    private java.util.Date pigeonholeDate;

    /**
     * 静态页路径**varchar(500)
     **/
    private String staticLink;

    public String getContentId() {
        return this.contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return this.shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginUrl() {
        return this.originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.Date getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(java.util.Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMediaPath() {
        return this.mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Integer getIsBold() {
        return this.isBold;
    }

    public void setIsBold(Integer isBold) {
        this.isBold = isBold;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
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

    public String getTypeImg() {
        return this.typeImg;
    }

    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTplContent() {
        return this.tplContent;
    }

    public void setTplContent(String tplContent) {
        this.tplContent = tplContent;
    }

    public Integer getNeedRegenerate() {
        return this.needRegenerate;
    }

    public void setNeedRegenerate(Integer needRegenerate) {
        this.needRegenerate = needRegenerate;
    }

    public java.util.Date getToplevelDate() {
        return this.toplevelDate;
    }

    public void setToplevelDate(java.util.Date toplevelDate) {
        this.toplevelDate = toplevelDate;
    }

    public java.util.Date getPigeonholeDate() {
        return this.pigeonholeDate;
    }

    public void setPigeonholeDate(java.util.Date pigeonholeDate) {
        this.pigeonholeDate = pigeonholeDate;
    }

    public String getStaticLink() {
        return staticLink;
    }

    public void setStaticLink(String staticLink) {
        this.staticLink = staticLink;
    }
}
