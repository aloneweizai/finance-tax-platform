package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * CMS专题表
 **/
@SuppressWarnings("serial")
public class TopicBo implements Serializable {

    /**
     * topicId**varchar(64)
     **/
    private String topicId;

    /**
     * 站点ID**varchar(64)
     **/
    private String siteId;

    /**
     * 域名**varchar(50)
     **/
    private String domain;

    /**
     * 路径**varchar(20)
     **/
    private String sitePath;

    /**
     * 站点名称**varchar(100)
     **/
    private String siteName;

    /**
     * channelId**varchar(64)
     **/
    private String channelId;

    /**
     * 名称**varchar(150)
     **/
    private String topicName;

    /**
     * 简称**varchar(150)
     **/
    private String shortName;

    /**
     * 关键字**varchar(255)
     **/
    private String keywords;

    /**
     * 描述**varchar(255)
     **/
    private String description;

    /**
     * 标题图**varchar(150)
     **/
    private String titleImg;

    /**
     * 内容图**varchar(150)
     **/
    private String contentImg;

    /**
     * 专题模板**varchar(100)
     **/
    private String tplContent;

    /**
     * 排列顺序**int(11)
     **/
    private Integer priority;

    /**
     * 是否推??**tinyint(1)
     **/
    private Integer isRecommend;

    public String getTopicId() {
        return this.topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTopicName() {
        return this.topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getTplContent() {
        return this.tplContent;
    }

    public void setTplContent(String tplContent) {
        this.tplContent = tplContent;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getIsRecommend() {
        return this.isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
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
}
