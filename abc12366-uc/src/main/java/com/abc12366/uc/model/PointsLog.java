package com.abc12366.uc.model;

import java.util.Date;

/**
 * @author liuguiyao<435720953@qq.com.com>
 * @create 2017-05-15 10:18 PM
 * @since 2.0.0
 */
public class PointsLog {
    private String id;
    private String userId;
    private String ruleId;
    private int income;
    private int outgo;
    private int usablePoints;
    private Date createTime;
    private String logType;
    private String remark;

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public PointsLog() {
    }

    public PointsLog(String id, String userId, String ruleId, int income, int outgo, int usablePoints, Date
            createTime) {
        this.id = id;
        this.userId = userId;
        this.ruleId = ruleId;
        this.income = income;
        this.outgo = outgo;
        this.usablePoints = usablePoints;
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

    public int getUsablePoints() {
        return usablePoints;
    }

    public void setUsablePoints(int usablePoints) {
        this.usablePoints = usablePoints;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PointsLog{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", ruleId='" + ruleId + '\'' +
                ", income=" + income +
                ", outgo=" + outgo +
                ", usablePoints=" + usablePoints +
                ", createTime=" + createTime +
                '}';
    }
}
