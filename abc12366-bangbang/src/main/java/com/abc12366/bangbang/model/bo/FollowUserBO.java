package com.abc12366.bangbang.model.bo;

import java.util.Date;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-06
 * Time: 17:38
 */
public class FollowUserBO {
    private String id;
    private String userId;
    private String followedUserId;
    private Date createTime;
    private Date lastUpdate;
    private String answerCount;
    private String askCount;
    private String followedCount;

    public FollowUserBO() {
    }

    public String getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(String answerCount) {
        this.answerCount = answerCount;
    }

    public String getAskCount() {
        return askCount;
    }

    public void setAskCount(String askCount) {
        this.askCount = askCount;
    }

    public String getFollowedCount() {
        return followedCount;
    }

    public void setFollowedCount(String followedCount) {
        this.followedCount = followedCount;
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

    public String getFollowedUserId() {
        return followedUserId;
    }

    public void setFollowedUserId(String followedUserId) {
        this.followedUserId = followedUserId;
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
}
