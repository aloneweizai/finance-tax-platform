package com.abc12366.bangbang.model.event;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by stuy on 2017/9/14.
 */
public class EventIdBo implements Serializable {
    private String eventid;
    private String title;
    private String province;
    private String city;
    private String address;
    private String picture;
    private Date begintime;

    private EventBrowseCountBo eventBrowseCountBo;

    private List<EventIdBo> eventIdBoList;

    public List<EventIdBo> getEventIdBoList() {
        return eventIdBoList;
    }

    public void setEventIdBoList(List<EventIdBo> eventIdBoList) {
        this.eventIdBoList = eventIdBoList;
    }

    public EventBrowseCountBo getEventBrowseCountBo() {
        return eventBrowseCountBo;
    }

    public void setEventBrowseCountBo(EventBrowseCountBo eventBrowseCountBo) {
        this.eventBrowseCountBo = eventBrowseCountBo;
    }

    private EventSponsorBbBo eventSponsorBbBo;

    private List<EventApplyFieldBo> eventApplyFieldBoList;

    public List<EventApplyFieldBo> getEventApplyFieldBoList() {
        return eventApplyFieldBoList;
    }

    public void setEventApplyFieldBoList(List<EventApplyFieldBo> eventApplyFieldBoList) {
        this.eventApplyFieldBoList = eventApplyFieldBoList;
    }

    public EventSponsorBbBo getEventSponsorBbBo() {
        return eventSponsorBbBo;
    }

    public void setEventSponsorBbBo(EventSponsorBbBo eventSponsorBbBo) {
        this.eventSponsorBbBo = eventSponsorBbBo;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPeoppleNum() {
        return peoppleNum;
    }

    public void setPeoppleNum(int peoppleNum) {
        this.peoppleNum = peoppleNum;
    }

    public String getSergrade() {
        return sergrade;
    }

    public void setSergrade(String sergrade) {
        this.sergrade = sergrade;
    }

    public String getSponsorid() {
        return sponsorid;
    }

    public void setSponsorid(String sponsorid) {
        this.sponsorid = sponsorid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBmendtime() {
        return bmendtime;
    }

    public void setBmendtime(Date bmendtime) {
        this.bmendtime = bmendtime;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getIsUserGrade() {
        return isUserGrade;
    }

    public void setIsUserGrade(String isUserGrade) {
        this.isUserGrade = isUserGrade;
    }

    private Date endtime;
    private String description;
    private int peoppleNum;
    private String sergrade;
    private String sponsorid;
    private String status;
    private Date bmendtime;
    private String isCheck;
    private String isUserGrade;
    private int isApply;

    public int getIsApply() {
        return isApply;
    }

    public void setIsApply(int isApply) {
        this.isApply = isApply;
    }
}
