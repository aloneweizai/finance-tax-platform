package com.abc12366.uc.model;

/**
 * 用户签到排行返回参数实体类
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-21
 * Time: 17:00
 */
public class CheckRank {
    //用户ID
    private String userId;
    //用户头像图片路径
    private String userPicturePath;
    //用户昵称
    private String nickname;
    //年内累计签到统计
    private Integer count;

    public CheckRank() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CheckRank{" +
                "userId='" + userId + '\'' +
                ", userPicturePath='" + userPicturePath + '\'' +
                ", nickname='" + nickname + '\'' +
                ", count=" + count +
                '}';
    }
}
