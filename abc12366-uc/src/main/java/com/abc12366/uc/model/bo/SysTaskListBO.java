package com.abc12366.uc.model.bo;

import java.util.Date;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-24
 * Time: 9:56
 */
public class SysTaskListBO {
    /**
     *系统任务ID
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
     * 系统任务奖励值
     */
    private int points;

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
     * 系统任务完成数量
     */
    private String finishedCount;
    /**
     * 系统任务编码
     */
    private String code;

    public SysTaskListBO() {
    }

    public String getFinishedCount() {
        return finishedCount;
    }

    public void setFinishedCount(String finishedCount) {
        this.finishedCount = finishedCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SysTaskListBO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", rule='" + rule + '\'' +
                ", points=" + points +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                ", imageUrl='" + imageUrl + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", ruleCode='" + ruleCode + '\'' +
                ", count=" + count +
                ", skipURL='" + skipURL + '\'' +
                ", finishedCount='" + finishedCount + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
