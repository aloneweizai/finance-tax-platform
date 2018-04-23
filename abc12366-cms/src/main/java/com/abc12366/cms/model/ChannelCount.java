package com.abc12366.cms.model;

import java.io.Serializable;


/**
 * CMS栏目访问量计数表
 **/
@SuppressWarnings("serial")
public class ChannelCount implements Serializable {

    /**
     * channelId**varchar(64)
     **/
    private String channelId;

    /**
     * 总访问数**int(11)
     **/
    private Integer views;

    /**
     * 月访问数**int(11)
     **/
    private Integer viewsMonth;

    /**
     * 周访问数**int(11)
     **/
    private Integer viewsWeek;

    /**
     * 日访问数**int(11)
     **/
    private Integer viewsDay;

    /**
     * 内容发布数**int(11)
     **/
    private Integer contentCountTotal;

    /**
     * 内容今日发布数**int(11)
     **/
    private Integer contentCountDay;

    /**
     * 内容本周发布数**int(11)
     **/
    private Integer contentCountWeek;

    /**
     * 内容本月发布数**int(11)
     **/
    private Integer contentCountMonth;

    /**
     * 内容今年发布数**int(11)
     **/
    private Integer contentCountYear;

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
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

    public Integer getContentCountTotal() {
        return this.contentCountTotal;
    }

    public void setContentCountTotal(Integer contentCountTotal) {
        this.contentCountTotal = contentCountTotal;
    }

    public Integer getContentCountDay() {
        return this.contentCountDay;
    }

    public void setContentCountDay(Integer contentCountDay) {
        this.contentCountDay = contentCountDay;
    }

    public Integer getContentCountWeek() {
        return this.contentCountWeek;
    }

    public void setContentCountWeek(Integer contentCountWeek) {
        this.contentCountWeek = contentCountWeek;
    }

    public Integer getContentCountMonth() {
        return this.contentCountMonth;
    }

    public void setContentCountMonth(Integer contentCountMonth) {
        this.contentCountMonth = contentCountMonth;
    }

    public Integer getContentCountYear() {
        return this.contentCountYear;
    }

    public void setContentCountYear(Integer contentCountYear) {
        this.contentCountYear = contentCountYear;
    }

}
