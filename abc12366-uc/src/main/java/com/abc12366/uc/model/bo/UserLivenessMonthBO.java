package com.abc12366.uc.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-11-24
 * Time: 16:26
 */
public class UserLivenessMonthBO {
    /**
     * 用户ID
     */
    private String userId;
    /**
     *昵称
     */
    private String nickname;
    /**
     *用户头像地址
     */
    private String userPicturePath;
    /**
     *当月登录数
     */
    private String cou;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }

    public String getCou() {
        return cou;
    }

    public void setCou(String cou) {
        this.cou = cou;
    }

    @Override
    public String toString() {
        return "UserLivenessMonthBO{" +
                "userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userPicturePath='" + userPicturePath + '\'' +
                ", cou='" + cou + '\'' +
                '}';
    }
}
