package com.abc12366.uc.model;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 用户签到入参实体类
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-21
 * Time: 14:40
 */
public class Check {
    private String id;
    //用户ID
    @NotEmpty
    private String userId;
    private Date createTime;
    private Date checkDate;
    private String orderby;
    //是否是补签
    private Boolean isReCheck;

    public Check() {
    }

    public Boolean getIsReCheck() {
        return isReCheck;
    }

    public void setIsReCheck(Boolean isReCheck) {
        this.isReCheck = isReCheck;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", checkDate=" + checkDate +
                ", orderby='" + orderby + '\'' +
                ", isReCheck=" + isReCheck +
                '}';
    }
}
