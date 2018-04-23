package com.abc12366.uc.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-24
 * Time: 10:26
 */
public class SysTaskInsertAndUpdateBO {
    @NotEmpty(message = "系统任务名称不能为空")
    @Size(max = 200)
    private String name;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @Size(max = 4000)
    private String rule;
    @NotNull(message = "奖励参数不能为空")
    private Integer award;
    @NotEmpty(message = "系统任务类型不能为空")
    @Size(max = 1)
    private String type;
    @NotNull(message = "系统任务状态不能为空")
    private Boolean status;
    @NotEmpty(message = "图片地址不能为空")
    @Size(max = 200)
    private String imageUrl;
    @Size(max = 20)
    private String ruleName;
    @Size(max = 20)
    private String ruleCode;
    @NotNull(message = "数量不能为空")
    private Integer count;
    @Size(max = 50)
    private String skipURL;
    @NotEmpty(message = "奖励类型不能为空")
    private String awardType;
    @NotEmpty(message = "规则ID不能为空")
    @Size(max = 64)
    private String ruleId;
    private String remark;
    @NotEmpty(message = "系统任务周期不能为空")
    private String dateType;
    @NotEmpty(message = "系统任务编码code不能为空")
    private String code;

    public SysTaskInsertAndUpdateBO() {
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getAwardType() {
        return awardType;
    }

    public void setAwardType(String awardType) {
        this.awardType = awardType;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SysTaskInsertAndUpdateBO{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", rule='" + rule + '\'' +
                ", award=" + award +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", imageUrl='" + imageUrl + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", ruleCode='" + ruleCode + '\'' +
                ", count=" + count +
                ", skipURL='" + skipURL + '\'' +
                ", awardType='" + awardType + '\'' +
                ", ruleId='" + ruleId + '\'' +
                ", remark='" + remark + '\'' +
                ", dateType='" + dateType + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
