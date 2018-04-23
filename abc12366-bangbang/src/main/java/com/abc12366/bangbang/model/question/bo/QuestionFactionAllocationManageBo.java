package com.abc12366.bangbang.model.question.bo;

import java.util.Date;

/**
 * @Author liuQi
 * @Date 2017/10/31 19:28
 * 运营管理系统 帮派成员奖励分配列表
 */
public class QuestionFactionAllocationManageBo {

    private String id;

    private String factionId;

    /*帮派名称*/
    private String factionName;

    /*成员名称*/
    private String memberName;

    /*成员等级*/
    private String memberLevel;

    /*分配积分*/
    private String integral;

    /*审核时间*/
    private Date updateTime;

    /*审核状态*/
    private Integer status;

    /*创建时间*/
    private Date createTime;

    /*拒绝理由*/
    private String refuseReason;


    public String getId() {
        return id;
    }

    public QuestionFactionAllocationManageBo setId(String id) {
        this.id = id;
        return this;
    }

    public String getFactionId() {
        return factionId;
    }

    public QuestionFactionAllocationManageBo setFactionId(String factionId) {
        this.factionId = factionId;
        return this;
    }

    public String getFactionName() {
        return factionName;
    }

    public QuestionFactionAllocationManageBo setFactionName(String factionName) {
        this.factionName = factionName;
        return this;
    }

    public String getMemberName() {
        return memberName;
    }

    public QuestionFactionAllocationManageBo setMemberName(String memberName) {
        this.memberName = memberName;
        return this;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public QuestionFactionAllocationManageBo setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
        return this;
    }

    public String getIntegral() {
        return integral;
    }

    public QuestionFactionAllocationManageBo setIntegral(String integral) {
        this.integral = integral;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public QuestionFactionAllocationManageBo setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public QuestionFactionAllocationManageBo setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public QuestionFactionAllocationManageBo setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public QuestionFactionAllocationManageBo setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
        return this;
    }
}
