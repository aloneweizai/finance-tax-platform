package com.abc12366.message.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2018-01-22
 * Time: 18:10
 */
public class UserSimple {
    private String id;
    private String username;
    private String userPicturePath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }

    @Override
    public String toString() {
        return "UserSimple{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", userPicturePath='" + userPicturePath + '\'' +
                '}';
    }
}
