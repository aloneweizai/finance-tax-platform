package com.abc12366.uc.model.bo;

import java.util.Date;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-25
 * Time: 17:04
 */
public class ExpComputeLogParam {
    private String userId;
    private String uexpCodexId;
    private String timeType;
    private Date starTime;
    private Date endTime;
    private String ruleId;

    public ExpComputeLogParam() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUexpCodexId() {
        return uexpCodexId;
    }

    public void setUexpCodexId(String uexpCodexId) {
        this.uexpCodexId = uexpCodexId;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public Date getStarTime() {
        return starTime;
    }

    public void setStarTime(Date starTime) {
        this.starTime = starTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }
}
