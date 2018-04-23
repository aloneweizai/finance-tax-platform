package com.abc12366.bangbang.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-09
 * Time: 11:59
 */
public class AskInsertBO {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String ask;
    private String detail;
    @NotEmpty
    @Size(max = 1)
    private String status;
    @Min(0)
    private int points;
    private Boolean isSolve;
    private String askedUserId;
    @NotEmpty
    @Size(max = 2)
    private String type;

    public AskInsertBO() {
    }

    public AskInsertBO(String userId, String ask, String detail, String status, int points, Boolean isSolve, String
            askedUserId, String type) {
        this.userId = userId;
        this.ask = ask;
        this.detail = detail;
        this.status = status;
        this.points = points;
        this.isSolve = isSolve;
        this.askedUserId = askedUserId;
        this.type = type;
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
        return "AskInsertBO{" +
                "userId='" + userId + '\'' +
                ", ask='" + ask + '\'' +
                ", detail='" + detail + '\'' +
                ", status='" + status + '\'' +
                ", points=" + points +
                ", isSolve=" + isSolve +
                ", askedUserId='" + askedUserId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
