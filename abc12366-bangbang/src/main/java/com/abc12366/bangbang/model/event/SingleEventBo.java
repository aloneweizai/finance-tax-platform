package com.abc12366.bangbang.model.event;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by stuy on 2017/9/13.
 */
public class SingleEventBo implements Serializable {

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public int getPeoppleNum() {
        return peoppleNum;
    }

    public void setPeoppleNum(int peoppleNum) {
        this.peoppleNum = peoppleNum;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    /**
     * 活动id
     */

    private String eventId;
    /*
    省
     */
    private String province;
    /**
     * 市区
     */
    private String city;
    /**
     * 地址
     */
    private String address;
    /**
     * 活动图片
     */
    private String picture;
    /**
     * 开始时间
     */
    private Date begintime;
    /**
     * 结束时间
     */
    private Date endtime;
    /**
     * 限制参与人数
     */
    private int peoppleNum;
    /**
     * 主办方名称
     */
    private String sponsorName;

    /**
     * 报名截止时间
     */
    private Date bmendtime;

    private String title;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBmendtime() {
        return bmendtime;
    }

    public void setBmendtime(Date bmendtime) {
        this.bmendtime = bmendtime;
    }
}
