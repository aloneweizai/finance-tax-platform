package com.abc12366.cms.model.bo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


/**
 * CMS内容扩展表
 * add by xieyanmao on 2017-4-25
 **/
@SuppressWarnings("serial")
public class ContentExtBo implements Serializable {

    /**
     * contentId**varchar(64)
     **/
    private String contentId;

    /**
     * 标题**varchar(600)
     **/
    @NotBlank
    @Size(max = 200)
    private String title;

    /**
     * 简短标题**varchar(1000)
     **/
    @Size(max = 330)
    private String shortTitle;

    /**
     * 作者**varchar(100)
     **/
    @Size(max = 30)
    private String author;

    /**
     * 来源**varchar(100)
     **/
    @Size(max = 30)
    private String origin;

    /**
     * 描述**varchar(255)
     **/
    @Size(max = 85)
    private String description;

    /**
     * 发布日期**datetime
     **/
    private java.util.Date releaseDate;

    /**
     * 媒体路径**varchar(255)
     **/
    @Size(max = 85)
    private String mediaPath;

    /**
     * 媒体类型**varchar(200)
     **/
    @Size(max = 65)
    private String mediaType;

    /**
     * 标题颜色**varchar(10)
     **/
    @Size(max = 10)
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
     * 外部链接**varchar(255)
     **/
    @Size(max = 100)
    private String link;

    /**
     * 指定模板**varchar(100)
     **/
    private String tplContent;

    /**
     * 静态页路径**varchar(500)
     **/
    private String staticLink;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTplContent() {
        return tplContent;
    }

    public void setTplContent(String tplContent) {
        this.tplContent = tplContent;
    }

    public String getStaticLink() {
        return staticLink;
    }

    public void setStaticLink(String staticLink) {
        this.staticLink = staticLink;
    }
}
