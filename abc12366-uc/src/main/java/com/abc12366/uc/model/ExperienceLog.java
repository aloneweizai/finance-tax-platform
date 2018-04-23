package com.abc12366.uc.model;

import java.util.Date;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 14:41
 */
public class ExperienceLog {
    private String id;
    private String userId;
    private String ruleId;
    private int income;
    private int outgo;
    private int usableExp;
    private Date createTime;

    public ExperienceLog() {
    }

    public ExperienceLog(String id, String userId, String ruleId, int income, int outgo, int usableExp, Date
            createTime) {
        this.id = id;
        this.userId = userId;
        this.ruleId = ruleId;
        this.income = income;
        this.outgo = outgo;
        this.usableExp = usableExp;
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

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getOutgo() {
        return outgo;
    }

    public void setOutgo(int outgo) {
        this.outgo = outgo;
    }

    public int getUsableExp() {
        return usableExp;
    }

    public void setUsableExp(int usableExp) {
        this.usableExp = usableExp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ExperienceLog{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", ruleId='" + ruleId + '\'' +
                ", income=" + income +
                ", outgo=" + outgo +
                ", usableExp=" + usableExp +
                ", createTime=" + createTime +
                '}';
    }
}
