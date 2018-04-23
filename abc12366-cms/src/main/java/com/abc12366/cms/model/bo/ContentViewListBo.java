package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.Date;


/**
 * CMS内容
 * add by xieyanmao on 2017-4-25
 **/
@SuppressWarnings("serial")
public class ContentViewListBo implements Serializable {

    /**
     * 内容ID
     **/
    private String contentId;

    /**
     * 标题
     **/
    private String title;

    /**
     * 标题图片
     **/
    private String titleImg;

    /**
     * 发布日期
     **/
    private Date releaseDate;

    /**
     * 域名**varchar(50)
     **/
    private String domain;

    /**
     * 站点路径**varchar(500)
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

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getStaticLink() {
        return staticLink;
    }

    public void setStaticLink(String staticLink) {
        this.staticLink = staticLink;
    }
}
