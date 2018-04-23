package com.abc12366.cms.model.bo;

import java.io.Serializable;
import java.util.List;


/**
 * CMS评论表
 * add by xieyanmao on 2017-4-25
 **/
@SuppressWarnings("serial")
public class CommentTjListBo implements Serializable {
    //今日评论数
    private Integer days;
    //本周评论数
    private Integer weeks;
    //本月评论数
    private Integer months;
    //本年评论数
    private Integer years;
    //总评论数
    private Integer cnts;

    //日统计全部评论
    private List<CommentTjBo> tjday;
    //日统计未回复
    private List<CommentTjBo> tjday0;
    //日统计已回复
    private List<CommentTjBo> tjday1;
    //月统计全部评论
    private List<CommentTjBo> tjmonth;
    //月统计未回复
    private List<CommentTjBo> tjmonth0;
    //月统计已回复
    private List<CommentTjBo> tjmonth1;
    //年统计全部评论
    private List<CommentTjBo> tjyear;
    //年统计未回复
    private List<CommentTjBo> tjyear0;
    //年统计已回复
    private List<CommentTjBo> tjyear1;

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getWeeks() {
        return weeks;
    }

    public void setWeeks(Integer weeks) {
        this.weeks = weeks;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getCnts() {
        return cnts;
    }

    public void setCnts(Integer cnts) {
        this.cnts = cnts;
    }

    public List<CommentTjBo> getTjday() {
        return tjday;
    }

    public void setTjday(List<CommentTjBo> tjday) {
        this.tjday = tjday;
    }

    public List<CommentTjBo> getTjday0() {
        return tjday0;
    }

    public void setTjday0(List<CommentTjBo> tjday0) {
        this.tjday0 = tjday0;
    }

    public List<CommentTjBo> getTjday1() {
        return tjday1;
    }

    public void setTjday1(List<CommentTjBo> tjday1) {
        this.tjday1 = tjday1;
    }

    public List<CommentTjBo> getTjmonth() {
        return tjmonth;
    }

    public void setTjmonth(List<CommentTjBo> tjmonth) {
        this.tjmonth = tjmonth;
    }

    public List<CommentTjBo> getTjmonth0() {
        return tjmonth0;
    }

    public void setTjmonth0(List<CommentTjBo> tjmonth0) {
        this.tjmonth0 = tjmonth0;
    }

    public List<CommentTjBo> getTjmonth1() {
        return tjmonth1;
    }

    public void setTjmonth1(List<CommentTjBo> tjmonth1) {
        this.tjmonth1 = tjmonth1;
    }

    public List<CommentTjBo> getTjyear() {
        return tjyear;
    }

    public void setTjyear(List<CommentTjBo> tjyear) {
        this.tjyear = tjyear;
    }

    public List<CommentTjBo> getTjyear0() {
        return tjyear0;
    }

    public void setTjyear0(List<CommentTjBo> tjyear0) {
        this.tjyear0 = tjyear0;
    }

    public List<CommentTjBo> getTjyear1() {
        return tjyear1;
    }

    public void setTjyear1(List<CommentTjBo> tjyear1) {
        this.tjyear1 = tjyear1;
    }
}
