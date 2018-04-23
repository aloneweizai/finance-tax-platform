package com.abc12366.bangbang.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-09
 * Time: 10:47
 */
public class AsksQueryParamBO {
    private String ask;
    private String detail;
    private String type;
    private String status;
    private Boolean isSolve;
    private String userId;
    private String askedUserId;

    public AsksQueryParamBO() {
    }

    public AsksQueryParamBO(String ask, String detail, String type, String status, Boolean isSolve, String userId,
                            String askedUserId) {
        this.ask = ask;
        this.detail = detail;
        this.type = type;
        this.status = status;
        this.isSolve = isSolve;
        this.userId = userId;
        this.askedUserId = askedUserId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsSolve() {
        return isSolve;
    }

    public void setIsSolve(Boolean isSolve) {
        this.isSolve = isSolve;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAskedUserId() {
        return askedUserId;
    }

    public void setAskedUserId(String askedUserId) {
        this.askedUserId = askedUserId;
    }

    @Override
    public String toString() {
        return "AsksQueryParamBO{" +
                "ask='" + ask + '\'' +
                ", detail='" + detail + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", isSolve=" + isSolve +
                ", userId='" + userId + '\'' +
                ", askedUserId='" + askedUserId + '\'' +
                '}';
    }
}
