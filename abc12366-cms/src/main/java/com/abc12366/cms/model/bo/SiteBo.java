package com.abc12366.cms.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * CMS站点表
 **/
@SuppressWarnings("serial")
public class SiteBo implements Serializable {

    /**
     * 站点Id**varchar(64)
     **/
    private String siteId;

    /**
     * 域名**varchar(50)
     **/
    @NotEmpty(message = "域名不能为空！")
    @Size(min = 1, max = 50)
    private String domain;

    /**
     * 路径**varchar(20)
     **/
    @NotEmpty(message = "站点路径不能为空！")
    @Size(min = 1, max = 20)
    private String sitePath;

    /**
     * 网站名称**varchar(100)
     **/
    @NotEmpty(message = "站点名称不能为空！")
    @Size(min = 1, max = 100)
    private String siteName;

    /**
     * 前台本地化**varchar(10)
     **/
    @Size(min = 0, max = 10)
    private String localeFront;

    /**
     * 站点关键字**varchar(255)
     **/
    @Size(min = 0, max = 255)
    private String keywords;

    /**
     * 站点描述**varchar(255)
     **/
    @Size(min = 0, max = 255)
    private String description;

    /**
     * 站点Logo**varchar(255)
     **/
    @Size(min = 0, max = 255)
    private String siteLogo;

    /**
     * 站点Logo图片地址**List
     **/
    @Size(min = 0, max = 250)
    private String siteLogoPath;

    /**
     * 版权说明**varchar(100)
     **/
    @Size(min = 0, max = 100)
    private String copyrightExplain;

    /**
     * 备案号**varchar(30)
     **/
    @Size(min = 0, max = 30)
    private String recordNum;

    /**
     * 资源是否自动同步**varchar(1)
     **/
    @Size(min = 0, max = 1)
    private String resourceSync;

    /**
     * 静态页是否自动同步**varchar(1)
     **/
    @Size(min = 0, max = 1)
    private String staticSync;

    /**
     * 静态发布**varchar(1)
     **/
    @Size(min = 0, max = 1)
    private String staticIssue;

    /**
     * 站点状态**varchar(1)
     **/
    @Size(min = 0, max = 1)
    private String siteStatus;

    /**
     * 第三方分享代码**varchar(500)
     **/
    private String shareCode;

    /**
     * 站点统计代码**varchar(500)
     **/
    private String statisticsCode;

    /**声明*/
    private String statement;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
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

    public String getLocaleFront() {
        return localeFront;
    }

    public void setLocaleFront(String localeFront) {
        this.localeFront = localeFront;
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

    public String getSiteLogo() {
        return siteLogo;
    }

    public void setSiteLogo(String siteLogo) {
        this.siteLogo = siteLogo;
    }

    public String getSiteLogoPath() {
        return siteLogoPath;
    }

    public void setSiteLogoPath(String siteLogoPath) {
        this.siteLogoPath = siteLogoPath;
    }

    public String getCopyrightExplain() {
        return copyrightExplain;
    }

    public void setCopyrightExplain(String copyrightExplain) {
        this.copyrightExplain = copyrightExplain;
    }

    public String getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(String recordNum) {
        this.recordNum = recordNum;
    }

    public String getResourceSync() {
        return resourceSync;
    }

    public void setResourceSync(String resourceSync) {
        this.resourceSync = resourceSync;
    }

    public String getStaticSync() {
        return staticSync;
    }

    public void setStaticSync(String staticSync) {
        this.staticSync = staticSync;
    }

    public String getStaticIssue() {
        return staticIssue;
    }

    public void setStaticIssue(String staticIssue) {
        this.staticIssue = staticIssue;
    }

    public String getSiteStatus() {
        return siteStatus;
    }

    public void setSiteStatus(String siteStatus) {
        this.siteStatus = siteStatus;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

    public String getStatisticsCode() {
        return statisticsCode;
    }

    public void setStatisticsCode(String statisticsCode) {
        this.statisticsCode = statisticsCode;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
