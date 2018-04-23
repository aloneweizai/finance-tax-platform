package com.abc12366.uc.model;

import java.util.Date;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-19
 * Time: 15:47
 */
public class VipLog {
    private String id;
    private String userId;
    private String source;
    private String levelId;
    private Date createTime;
    private Date vipExpireDate;

    public VipLog() {
    }

    public VipLog(String id, String userId, String source, String levelId, Date createTime, Date vipExpireDate) {
        this.id = id;
        this.userId = userId;
        this.source = source;
        this.levelId = levelId;
        this.createTime = createTime;
        this.vipExpireDate = vipExpireDate;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getVipExpireDate() {
        return vipExpireDate;
    }

    public void setVipExpireDate(Date vipExpireDate) {
        this.vipExpireDate = vipExpireDate;
    }

    @Override
    public String toString() {
        return "VipLog{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", source='" + source + '\'' +
                ", levelId='" + levelId + '\'' +
                ", createTime=" + createTime +
                ", vipExpireDate=" + vipExpireDate +
                '}';
    }
}
