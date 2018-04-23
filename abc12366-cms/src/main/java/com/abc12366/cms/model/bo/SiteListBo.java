package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * CMS站点表
 **/
@SuppressWarnings("serial")
public class SiteListBo implements Serializable {

    /**
     * siteId**varchar(64)
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
     * 网站名称**varchar(100)
     **/
    private String siteName;

    /**
     * 前台本地化**varchar(10)
     **/
    private String localeFront;

    /**
     * 站点描述**varchar(255)
     **/
    private String description;

    /**
     * 站点Logo**varchar(255)
     **/
    private String siteLogo;

    /**
     * 版权说明**varchar(100)
     **/
    private String copyrightExplain;

    /**
     * 备案号**varchar(30)
     **/
    private String recordNum;

    /**
     * 静态发布**tinyint(1)
     **/
    private String staticIssue;

    /**
     * 站点状态**tinyint(1)
     **/
    private String siteStatus;

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
}
