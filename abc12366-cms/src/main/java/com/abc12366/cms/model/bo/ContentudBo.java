package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * CMS内容表
 * add by xieyanmao on 2017-4-25
 **/
@SuppressWarnings("serial")
public class ContentudBo implements Serializable {

    /**
     * contentId**varchar(64)
     **/
    private String contentId;

    /**
     * 访问路径**varchar(30)
     **/
    private String channelPath;

    /**
     * 标题**varchar(64)
     **/
    private String title;

    /**
     * 站点路径**varchar(20)
     **/
    private String sitePath;

    /**
     * 站点域名**varchar
     **/
    private String domain;

    /**
     * 上下篇**tinyint(4)
     **/
    private String upordown;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getChannelPath() {
        return channelPath;
    }

    public void setChannelPath(String channelPath) {
        this.channelPath = channelPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSitePath() {
        return sitePath;
    }

    public void setSitePath(String sitePath) {
        this.sitePath = sitePath;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUpordown() {
        return upordown;
    }

    public void setUpordown(String upordown) {
        this.upordown = upordown;
    }
}
