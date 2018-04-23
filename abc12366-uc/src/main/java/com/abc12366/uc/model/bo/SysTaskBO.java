package com.abc12366.uc.model.bo;

import java.util.Date;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-24
 * Time: 9:56
 */
public class SysTaskBO {
    /**
     * 系统任务ID
     */
    private String id;

    /**
     * 系统任务名称
     */
    private String name;

    /**
     * 系统任务开始时间
     */
    private Date startTime;

    /**
     * 系统任务结束时间
     */
    private Date endTime;

    /**
     * 系统任务规则
     */
    private String rule;
    /**
     * 系统任务奖励
     */
    private Integer award;
    /**
     * 系统任务类型
     */
    private String type;
    /**
     * 系统任务状态
     */
    private boolean status;
    /**
     * 系统任务创建时间
     */
    private Date createTime;
    /**
     * 系统任务最后修改时间
     */
    private Date lastUpdate;
    /**
     * 系统任务图片路径
     */
    private String imageUrl;
    /**
     * 系统任务规则名称
     */
    private String ruleName;
    /**
     * 系统任务规则代码
     */
    private String ruleCode;
    /**
     * 系统任务数量
     */
    private Integer count;
    /**
     * 系统任务跳转地址
     */
    private String skipURL;
    /**
     * 系统任务奖励类型
     */
    private String awardType;
    /**
     * 系统任务备注
     */
    private String remark;
    /**
     * 系统任务周期类型
     */
    private String dateType;
    /**
     * 系统任务关联的规则ID
     */
    private String ruleId;
    /**
     * 系统任务编码
     */
    private String code;

    public SysTaskBO() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSkipURL() {
        return skipURL;
    }

    public void setSkipURL(String skipURL) {
        this.skipURL = skipURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Integer getAward() {
        return award;
    }

    public void setAward(Integer award) {
        this.award = award;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getAwardType() {
        return awardType;
    }

    public void setAwardType(String awardType) {
        this.awardType = awardType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SysTaskBO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", rule='" + rule + '\'' +
                ", award=" + award +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                ", imageUrl='" + imageUrl + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", ruleCode='" + ruleCode + '\'' +
                ", count=" + count +
                ", skipURL='" + skipURL + '\'' +
                ", awardType='" + awardType + '\'' +
                ", remark='" + remark + '\'' +
                ", dateType='" + dateType + '\'' +
                ", ruleId='" + ruleId + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
