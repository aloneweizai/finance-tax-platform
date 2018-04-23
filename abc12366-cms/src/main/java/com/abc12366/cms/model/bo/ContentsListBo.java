package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.Date;


/**
 * CMS内容表-供模板生成用
 * add by xieyanmao on 2017-4-25
 **/
@SuppressWarnings("serial")
public class ContentsListBo implements Serializable {

    /**
     * 内容ID
     **/
    private String contentId;

    /**
     * userid**varchar(64)
     **/
    private String userid;

    /**
     * username**varchar(100)
     **/
    private String username;

    /**
     * 标题
     **/
    private String title;

    /**
     * 简短标题
     **/
    private String shortTitle;

    /**
     * 标题颜色**varchar(10)
     **/
    private String titleColor;

    /**
     * 栏目ID
     **/
    private String channelId;

    /**
     * 栏目名称
     **/
    private String channelName;

    /**
     * 固顶级别
     **/
    private Integer topLevel;

    /**
     * 属性ID
     **/
    private String typeId;

    /**
     * 内容类型ID
     **/
    private String contentType;

    /**
     * 描述
     **/
    private String description;

    /**
     * 标题图片
     **/
    private String titleImg;

    /**
     * 作者
     **/
    private String author;

    /**
     * 总访问数
     **/
    private String views;

    /**
     * 发布日期
     **/
    private Date releaseDate;

    /**
     * 状态(0:草稿;1:审核中;2:审核通过;3:回收站;4:投稿;5:归档)
     **/
    private Integer status;

    /**
     * 推荐级别
     **/
    private Integer recommendLevel;

    /**
     * 访问路径**varchar(30)
     **/
    private String channelPath;

    /**
     * 域名**varchar(50)
     **/
    private String domain;

    /**
     * 站点路径**varchar(20)
     **/
    private String sitePath;

    /**
     * 站点名称**varchar(100)
     **/
    private String siteName;

    /**
     * 站点路径**varchar(500)
     **/
    private String staticLink;

    /**
     * 来源**varchar(100)
     **/
    private String origin;


    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public Integer getRecommendLevel() {
        return recommendLevel;
    }

    public void setRecommendLevel(Integer recommendLevel) {
        this.recommendLevel = recommendLevel;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getTopLevel() {
        return topLevel;
    }

    public void setTopLevel(Integer topLevel) {
        this.topLevel = topLevel;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getChannelPath() {
        return channelPath;
    }

    public void setChannelPath(String channelPath) {
        this.channelPath = channelPath;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSitePath() {
        return sitePath;
    }

    public void setSitePath(String sitePath) {
        this.sitePath = sitePath;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getStaticLink() {
        return staticLink;
    }

    public void setStaticLink(String staticLink) {
        this.staticLink = staticLink;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
