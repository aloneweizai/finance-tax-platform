package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS站点表
 **/
@SuppressWarnings("serial")
public class Site implements Serializable {

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
     * 简短名称**varchar(100)
     **/
    private String shortName;

    /**
     * 协议**varchar(20)
     **/
    private String protocol;

    /**
     * 动态页后缀**varchar(10)
     **/
    private String dynamicSuffix;

    /**
     * 静态页后缀**varchar(10)
     **/
    private String staticSuffix;

    /**
     * 静态页存放目录**varchar(50)
     **/
    private String staticDir;

    /**
     * 是否使用将首页放在根目录下**char(1)
     **/
    private String isIndexToRoot;

    /**
     * 是否静态化首页**char(1)
     **/
    private String isStaticIndex;

    /**
     * 后台本地化**varchar(10)
     **/
    private String localeAdmin;

    /**
     * 前台本地化**varchar(10)
     **/
    private String localeFront;

    /**
     * 模板方案**varchar(50)
     **/
    private String tplSolution;

    /**
     * 终审级别**tinyint(4)
     **/
    private Integer finalStep;

    /**
     * 审核后(1:不能修改删除;2:修改后退回;3:修改后不变)**tinyint(4)
     **/
    private Integer afterCheck;

    /**
     * 是否使用相对路径**char(1)
     **/
    private String isRelativePath;

    /**
     * 是否开启回收站**char(1)
     **/
    private String isRecycleOn;

    /**
     * 域名别名**varchar(255)
     **/
    private String domainAlias;

    /**
     * 域名重定向**varchar(255)
     **/
    private String domainRedirect;

    /**
     * 首页模板**varchar(255)
     **/
    private String tplIndex;

    /**
     * 站点关键字**varchar(255)
     **/
    private String keywords;

    /**
     * 站点描述**varchar(255)
     **/
    private String description;

    /**
     * 站点Logo**varchar(255)
     **/
    private String siteLogo;

    /**
     * 站点Logo图片地址**List
     **/
    private String siteLogoPath;

    /**
     * 版权说明**varchar(100)
     **/
    private String copyrightExplain;

    /**
     * 备案号**varchar(30)
     **/
    private String recordNum;

    /**
     * 资源是否自动同步**varchar(1)
     **/
    private String resourceSync;

    /**
     * 静态页是否自动同步**varchar(1)
     **/
    private String staticSync;

    /**
     * 静态发布**varchar(1)
     **/
    private String staticIssue;

    /**
     * 站点状态**varchar(1)
     **/
    private String siteStatus;

    /**
     * 第三方分享代码**varchar(500)
     **/
    private String shareCode;

    /**声明*/
    private String statement;

    /**
     * 站点统计代码**varchar(500)
     **/
    private String statisticsCode;

    public String getSiteId() {
        return this.siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSitePath() {
        return this.sitePath;
    }

    public void setSitePath(String sitePath) {
        this.sitePath = sitePath;
    }

    public String getSiteName() {
        return this.siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getDynamicSuffix() {
        return this.dynamicSuffix;
    }

    public void setDynamicSuffix(String dynamicSuffix) {
        this.dynamicSuffix = dynamicSuffix;
    }

    public String getStaticSuffix() {
        return this.staticSuffix;
    }

    public void setStaticSuffix(String staticSuffix) {
        this.staticSuffix = staticSuffix;
    }

    public String getStaticDir() {
        return this.staticDir;
    }

    public void setStaticDir(String staticDir) {
        this.staticDir = staticDir;
    }

    public String getIsIndexToRoot() {
        return this.isIndexToRoot;
    }

    public void setIsIndexToRoot(String isIndexToRoot) {
        this.isIndexToRoot = isIndexToRoot;
    }

    public String getIsStaticIndex() {
        return this.isStaticIndex;
    }

    public void setIsStaticIndex(String isStaticIndex) {
        this.isStaticIndex = isStaticIndex;
    }

    public String getLocaleAdmin() {
        return this.localeAdmin;
    }

    public void setLocaleAdmin(String localeAdmin) {
        this.localeAdmin = localeAdmin;
    }

    public String getLocaleFront() {
        return this.localeFront;
    }

    public void setLocaleFront(String localeFront) {
        this.localeFront = localeFront;
    }

    public String getTplSolution() {
        return this.tplSolution;
    }

    public void setTplSolution(String tplSolution) {
        this.tplSolution = tplSolution;
    }

    public Integer getFinalStep() {
        return this.finalStep;
    }

    public void setFinalStep(Integer finalStep) {
        this.finalStep = finalStep;
    }

    public Integer getAfterCheck() {
        return this.afterCheck;
    }

    public void setAfterCheck(Integer afterCheck) {
        this.afterCheck = afterCheck;
    }

    public String getIsRelativePath() {
        return this.isRelativePath;
    }

    public void setIsRelativePath(String isRelativePath) {
        this.isRelativePath = isRelativePath;
    }

    public String getIsRecycleOn() {
        return this.isRecycleOn;
    }

    public void setIsRecycleOn(String isRecycleOn) {
        this.isRecycleOn = isRecycleOn;
    }

    public String getDomainAlias() {
        return this.domainAlias;
    }

    public void setDomainAlias(String domainAlias) {
        this.domainAlias = domainAlias;
    }

    public String getDomainRedirect() {
        return this.domainRedirect;
    }

    public void setDomainRedirect(String domainRedirect) {
        this.domainRedirect = domainRedirect;
    }

    public String getTplIndex() {
        return this.tplIndex;
    }

    public void setTplIndex(String tplIndex) {
        this.tplIndex = tplIndex;
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

    public String getSiteLogo() {
        return this.siteLogo;
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
        return this.copyrightExplain;
    }

    public void setCopyrightExplain(String copyrightExplain) {
        this.copyrightExplain = copyrightExplain;
    }

    public String getRecordNum() {
        return this.recordNum;
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
