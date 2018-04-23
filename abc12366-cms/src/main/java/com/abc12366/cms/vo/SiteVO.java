package com.abc12366.cms.vo;

import java.io.Serializable;


/**
 * CMS站点表
 **/
@SuppressWarnings("serial")
public class SiteVO implements Serializable {

    /****/
    private String siteId;

    /**
     * 域名
     **/
    private String domain;

    /**
     * 路径
     **/
    private String sitePath;

    /**
     * 网站名称
     **/
    private String siteName;

    /**
     * 简短名称
     **/
    private String shortName;

    /**
     * 协议
     **/
    private String protocol;

    /**
     * 动态页后缀
     **/
    private String dynamicSuffix;

    /**
     * 静态页后缀
     **/
    private String staticSuffix;

    /**
     * 静态页存放目录
     **/
    private String staticDir;

    /**
     * 是否使用将首页放在根目录下
     **/
    private String isIndexToRoot;

    /**
     * 是否静态化首页
     **/
    private String isStaticIndex;

    /**
     * 后台本地化
     **/
    private String localeAdmin;

    /**
     * 前台本地化
     **/
    private String localeFront;

    /**
     * 模板方案
     **/
    private String tplSolution;

    /**
     * 终审级别
     **/
    private Integer finalStep;

    /**
     * 审核后(1:不能修改删除;2:修改后退回;3:修改后不变)
     **/
    private Integer afterCheck;

    /**
     * 是否使用相对路径
     **/
    private String isRelativePath;

    /**
     * 是否开启回收站
     **/
    private String isRecycleOn;

    /**
     * 域名别名
     **/
    private String domainAlias;

    /**
     * 域名重定向
     **/
    private String domainRedirect;

    /**
     * 首页模板
     **/
    private String tplIndex;

    /**
     * 站点关键字
     **/
    private String keywords;

    /**
     * 站点描述
     **/
    private String description;

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

}
