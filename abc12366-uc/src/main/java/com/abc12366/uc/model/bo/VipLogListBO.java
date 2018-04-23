package com.abc12366.uc.model.bo;

import java.util.Date;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-19
 * Time: 17:18
 */
public class VipLogListBO {
    private String id;
    private String userId;
    private String source;
    private String levelId;
    private String level;
    private Date createTime;

    public VipLogListBO() {
    }

    public VipLogListBO(String id, String userId, String source, String levelId, String level, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.source = source;
        this.levelId = levelId;
        this.level = level;
        this.createTime = createTime;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "VipLogListBO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", source='" + source + '\'' +
                ", levelId='" + levelId + '\'' +
                ", level='" + level + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
