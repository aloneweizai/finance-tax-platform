package com.abc12366.bangbang.model;

import java.util.Date;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-09
 * Time: 10:44
 */
public class Ask {
    private String id;
    private String userId;
    private String ask;
    private String detail;
    private String status;
    private Date createTime;
    private Date lastUpdate;
    private int points;
    private Boolean isSolve;
    private String askedUserId;
    private String type;

    public Ask() {
    }

    public Ask(String id, String userId, String ask, String detail, String status, Date createTime, Date lastUpdate,
               int points, Boolean isSolve, String askedUserId, String type) {
        this.id = id;
        this.userId = userId;
        this.ask = ask;
        this.detail = detail;
        this.status = status;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
        this.points = points;
        this.isSolve = isSolve;
        this.askedUserId = askedUserId;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Boolean getIsSolve() {
        return isSolve;
    }

    public void setIsSolve(Boolean isSolve) {
        this.isSolve = isSolve;
    }

    public String getAskedUserId() {
        return askedUserId;
    }

    public void setAskedUserId(String askedUserId) {
        this.askedUserId = askedUserId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ask{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", ask='" + ask + '\'' +
                ", detail='" + detail + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                ", points=" + points +
                ", isSolve=" + isSolve +
                ", askedUserId='" + askedUserId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
