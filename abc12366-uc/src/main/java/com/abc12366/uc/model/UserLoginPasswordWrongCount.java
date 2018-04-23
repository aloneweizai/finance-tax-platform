package com.abc12366.uc.model;

import java.util.Date;

/**
 * 用户登录连续输错密码记录bo
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-10
 * Time: 10:29
 */
public class UserLoginPasswordWrongCount {
    //主键
    private String id;
    //用户ID
    private String userId;
    //连续输错密码次数
    private int count;
    //账号锁定时间
    private Date limitTime;

    public UserLoginPasswordWrongCount() {
    }

    public UserLoginPasswordWrongCount(String id, String userId, int count, Date limitTime) {
        this.id = id;
        this.userId = userId;
        this.count = count;
        this.limitTime = limitTime;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Date limitTime) {
        this.limitTime = limitTime;
    }

    @Override
    public String toString() {
        return "UserLoginPasswordWrongCountBO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", count=" + count +
                ", limitTime=" + limitTime +
                '}';
    }
}
