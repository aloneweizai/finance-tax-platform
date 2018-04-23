package com.abc12366.uc.model.bo;

/**
 * 用户活跃度概况bo
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-11-27
 * Time: 18:08
 */
public class UserLivenessSurveyBO {
    /**
     * 昨日活跃用户数
     */
    private float yesterday;
    /**
     * 过去7日活跃用户数
     */
    private float lastweek;
    /**
     * 昨日活跃用户数除以过去7日活跃用户数
     */
    private String lastweekDevidedbyLastweek;
    /**
     * 过去30日活跃用户数
     */
    private float last30Days;
    /**
     * 昨日活跃用户数除以过去30日活跃用户数
     */
    private String Last30DaysDevidedbyYesterday;

    private float thismonth;

    public float getYesterday() {
        return yesterday;
    }

    public void setYesterday(float yesterday) {
        this.yesterday = yesterday;
    }

    public float getLastweek() {
        return lastweek;
    }

    public void setLastweek(float lastweek) {
        this.lastweek = lastweek;
    }

    public String getLastweekDevidedbyLastweek() {
        return lastweekDevidedbyLastweek;
    }

    public void setLastweekDevidedbyLastweek(String lastweekDevidedbyLastweek) {
        this.lastweekDevidedbyLastweek = lastweekDevidedbyLastweek;
    }

    public float getLast30Days() {
        return last30Days;
    }

    public void setLast30Days(float last30Days) {
        this.last30Days = last30Days;
    }

    public String getLast30DaysDevidedbyYesterday() {
        return Last30DaysDevidedbyYesterday;
    }

    public void setLast30DaysDevidedbyYesterday(String last30DaysDevidedbyYesterday) {
        Last30DaysDevidedbyYesterday = last30DaysDevidedbyYesterday;
    }

    public float getThismonth() {
        return thismonth;
    }

    public void setThismonth(float thismonth) {
        this.thismonth = thismonth;
    }

    @Override
    public String toString() {
        return "UserLivenessSurveyBO{" +
                "yesterday=" + yesterday +
                ", lastweek=" + lastweek +
                ", lastweekDevidedbyLastweek='" + lastweekDevidedbyLastweek + '\'' +
                ", last30Days=" + last30Days +
                ", Last30DaysDevidedbyYesterday='" + Last30DaysDevidedbyYesterday + '\'' +
                ", thismonth=" + thismonth +
                '}';
    }
}
