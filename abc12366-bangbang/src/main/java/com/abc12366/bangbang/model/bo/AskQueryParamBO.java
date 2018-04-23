package com.abc12366.bangbang.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-09
 * Time: 11:38
 */
public class AskQueryParamBO {
    private String ask;
    private String detail;
    private String type;
    private Boolean isSolve;
    private String userId;
    private String askedUserId;

    public AskQueryParamBO() {
    }

    public AskQueryParamBO(String ask, String detail, String type, Boolean isSolve, String userId, String askedUserId) {
        this.ask = ask;
        this.detail = detail;
        this.type = type;
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
        return "AskQueryParamBO{" +
                "ask='" + ask + '\'' +
                ", detail='" + detail + '\'' +
                ", type='" + type + '\'' +
                ", isSolve=" + isSolve +
                ", userId='" + userId + '\'' +
                ", askedUserId='" + askedUserId + '\'' +
                '}';
    }
}
