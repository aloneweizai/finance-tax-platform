package com.abc12366.uc.model;

import java.util.Date;

/**
 * 代办任务列表，用于前台向用户展现的BO
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-12
 * Time: 14:45
 */
public class TodoTaskFront {
    /**
     * 任务ID
     */
    private String id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 系统任务ID
     */
    private String sysTaskId;
    /**
     * 需完成数量
     */
    private Integer allCount;
    /**
     * 已完成数量
     */
    private Integer finishedCount;
    /**
     * 奖励类型
     */
    private String awardType;
    /**
     * 任务类型
     */
    private String type;
    /**
     * 奖励值
     */
    private Integer award;
    /**
     * 任务状态
     */
    private String status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后修改时间
     */
    private Date lastUpdate;
    /**
     * 跳转地址
     */
    private String skipUrl;
    /**
     * 规则ID
     */
    private String ruleId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 周期类型
     */
    private String dateType;
    /**
     * 任务名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 图片地址
     */
    private String imageUrl;
    /**
     * 规则名称
     */
    private String rule;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getSysTaskId() {
        return sysTaskId;
    }

    public void setSysTaskId(String sysTaskId) {
        this.sysTaskId = sysTaskId;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }

    public Integer getFinishedCount() {
        return finishedCount;
    }

    public void setFinishedCount(Integer finishedCount) {
        this.finishedCount = finishedCount;
    }

    public String getAwardType() {
        return awardType;
    }

    public void setAwardType(String awardType) {
        this.awardType = awardType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getAward() {
        return award;
    }

    public void setAward(Integer award) {
        this.award = award;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getSkipUrl() {
        return skipUrl;
    }

    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
