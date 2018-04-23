package com.abc12366.uc.model.bo;

/**
 * 用户活跃度统计bo
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-11-24
 * Time: 16:25
 */
public class UserLivenessDetailBO {
    /**
     * 日期区间
     */
    private String date;

    /**
     * 注册人数
     */
    private float register;

    /**
     * 活跃用户数
     */
    private float liveUsers;

    /**
     * 活跃用户率
     */
    private String liveUserPercent;

    /**
     * 总注册用户数
     */
    private float allRegister;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getRegister() {
        return register;
    }

    public void setRegister(float register) {
        this.register = register;
    }

    public float getLiveUsers() {
        return liveUsers;
    }

    public void setLiveUsers(float liveUsers) {
        this.liveUsers = liveUsers;
    }

    public String getLiveUserPercent() {
        return liveUserPercent;
    }

    public void setLiveUserPercent(String liveUserPercent) {
        this.liveUserPercent = liveUserPercent;
    }

    public float getAllRegister() {
        return allRegister;
    }

    public void setAllRegister(float allRegister) {
        this.allRegister = allRegister;
    }

    @Override
    public String toString() {
        return "UserLivenessDetailBO{" +
                "date='" + date + '\'' +
                ", register=" + register +
                ", liveUsers=" + liveUsers +
                ", liveUserPercent='" + liveUserPercent + '\'' +
                ", allRegister=" + allRegister +
                '}';
    }
}
