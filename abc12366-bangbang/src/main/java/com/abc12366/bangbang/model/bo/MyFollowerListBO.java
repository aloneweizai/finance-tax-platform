package com.abc12366.bangbang.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-21
 * Time: 14:23
 */
public class MyFollowerListBO {
    private String followerName;
    private String followerAnswerCount;
    private String followerAskCount;
    private String followerFansCount;
    private String userPicturePath;
    private String followerUserId;
    private Boolean isFollowed;

    public MyFollowerListBO() {
    }

    public String getFollowerUserId() {
        return followerUserId;
    }

    public void setFollowerUserId(String followerUserId) {
        this.followerUserId = followerUserId;
    }

    public Boolean getIsFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(Boolean isFollowed) {
        this.isFollowed = isFollowed;
    }

    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }

    public String getFollowerName() {
        return followerName;
    }

    public void setFollowerName(String followerName) {
        this.followerName = followerName;
    }

    public String getFollowerAnswerCount() {
        return followerAnswerCount;
    }

    public void setFollowerAnswerCount(String followerAnswerCount) {
        this.followerAnswerCount = followerAnswerCount;
    }

    public String getFollowerAskCount() {
        return followerAskCount;
    }

    public void setFollowerAskCount(String followerAskCount) {
        this.followerAskCount = followerAskCount;
    }

    public String getFollowerFansCount() {
        return followerFansCount;
    }

    public void setFollowerFansCount(String followerFansCount) {
        this.followerFansCount = followerFansCount;
    }
}
