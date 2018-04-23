package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS内容计数表
 **/
@SuppressWarnings("serial")
public class ContentCount implements Serializable {

    /**
     * contentId**varchar(64)
     **/
    private String contentId;

    /**
     * 总访问数**int(11)
     **/
    private Integer views = 0;

    /**
     * 月访问数**int(11)
     **/
    private Integer viewsMonth = 0;

    /**
     * 周访问数**int(11)
     **/
    private Integer viewsWeek = 0;

    /**
     * 日访问数**int(11)
     **/
    private Integer viewsDay = 0;

    /**
     * 总评论数**int(11)
     **/
    private Integer comments = 0;

    /**
     * 月评论数**int(11)
     **/
    private Integer commentsMonth = 0;

    /**
     * 周评论数**smallint(6)
     **/
    private Integer commentsWeek = 0;

    /**
     * 日评论数**smallint(6)
     **/
    private Integer commentsDay = 0;

    /**
     * 总下载数**int(11)
     **/
    private Integer downloads = 0;

    /**
     * 月下载数**int(11)
     **/
    private Integer downloadsMonth = 0;

    /**
     * 周下载数**smallint(6)
     **/
    private Integer downloadsWeek = 0;

    /**
     * 日下载数**smallint(6)
     **/
    private Integer downloadsDay = 0;

    /**
     * 总顶数**int(11)
     **/
    private Integer ups = 0;

    /**
     * 月顶数**int(11)
     **/
    private Integer upsMonth = 0;

    /**
     * 周顶数**smallint(6)
     **/
    private Integer upsWeek = 0;

    /**
     * 日顶数**smallint(6)
     **/
    private Integer upsDay = 0;

    /**
     * 总踩数**int(11)
     **/
    private Integer downs = 0;

    public String getContentId() {
        return this.contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Integer getViews() {
        return this.views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getViewsMonth() {
        return this.viewsMonth;
    }

    public void setViewsMonth(Integer viewsMonth) {
        this.viewsMonth = viewsMonth;
    }

    public Integer getViewsWeek() {
        return this.viewsWeek;
    }

    public void setViewsWeek(Integer viewsWeek) {
        this.viewsWeek = viewsWeek;
    }

    public Integer getViewsDay() {
        return this.viewsDay;
    }

    public void setViewsDay(Integer viewsDay) {
        this.viewsDay = viewsDay;
    }

    public Integer getComments() {
        return this.comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getCommentsMonth() {
        return this.commentsMonth;
    }

    public void setCommentsMonth(Integer commentsMonth) {
        this.commentsMonth = commentsMonth;
    }

    public Integer getCommentsWeek() {
        return this.commentsWeek;
    }

    public void setCommentsWeek(Integer commentsWeek) {
        this.commentsWeek = commentsWeek;
    }

    public Integer getCommentsDay() {
        return this.commentsDay;
    }

    public void setCommentsDay(Integer commentsDay) {
        this.commentsDay = commentsDay;
    }

    public Integer getDownloads() {
        return this.downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Integer getDownloadsMonth() {
        return this.downloadsMonth;
    }

    public void setDownloadsMonth(Integer downloadsMonth) {
        this.downloadsMonth = downloadsMonth;
    }

    public Integer getDownloadsWeek() {
        return this.downloadsWeek;
    }

    public void setDownloadsWeek(Integer downloadsWeek) {
        this.downloadsWeek = downloadsWeek;
    }

    public Integer getDownloadsDay() {
        return this.downloadsDay;
    }

    public void setDownloadsDay(Integer downloadsDay) {
        this.downloadsDay = downloadsDay;
    }

    public Integer getUps() {
        return this.ups;
    }

    public void setUps(Integer ups) {
        this.ups = ups;
    }

    public Integer getUpsMonth() {
        return this.upsMonth;
    }

    public void setUpsMonth(Integer upsMonth) {
        this.upsMonth = upsMonth;
    }

    public Integer getUpsWeek() {
        return this.upsWeek;
    }

    public void setUpsWeek(Integer upsWeek) {
        this.upsWeek = upsWeek;
    }

    public Integer getUpsDay() {
        return this.upsDay;
    }

    public void setUpsDay(Integer upsDay) {
        this.upsDay = upsDay;
    }

    public Integer getDowns() {
        return this.downs;
    }

    public void setDowns(Integer downs) {
        this.downs = downs;
    }

}
