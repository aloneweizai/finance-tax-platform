package com.abc12366.bangbang.model.question.bo;

import java.util.Date;

/**
 * @Author xieyanmao
 * @Date 2017/10/31 19:28
 * 帮派成员奖励分配列表
 */
public class QuestionFactionAllocationsBo {

    private String id;

    private String factionId;

    /*帮派名称*/
    private String factionName;

    /*成员名称*/
    private String memberName;

    /*成员图片*/
    private String memberImage;

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

    /**奖励理由**varchar(1000)**/
    private String awardReason;


    public String getId() {
        return id;
    }

    public QuestionFactionAllocationsBo setId(String id) {
        this.id = id;
        return this;
    }

    public String getFactionId() {
        return factionId;
    }

    public QuestionFactionAllocationsBo setFactionId(String factionId) {
        this.factionId = factionId;
        return this;
    }

    public String getFactionName() {
        return factionName;
    }

    public QuestionFactionAllocationsBo setFactionName(String factionName) {
        this.factionName = factionName;
        return this;
    }

    public String getMemberName() {
        return memberName;
    }

    public QuestionFactionAllocationsBo setMemberName(String memberName) {
        this.memberName = memberName;
        return this;
    }

    public String getMemberImage() {
        return memberImage;
    }

    public void setMemberImage(String memberImage) {
        this.memberImage = memberImage;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public QuestionFactionAllocationsBo setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
        return this;
    }

    public String getIntegral() {
        return integral;
    }

    public QuestionFactionAllocationsBo setIntegral(String integral) {
        this.integral = integral;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public QuestionFactionAllocationsBo setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public QuestionFactionAllocationsBo setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public QuestionFactionAllocationsBo setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public QuestionFactionAllocationsBo setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
        return this;
    }

    public String getAwardReason() {
        return awardReason;
    }

    public void setAwardReason(String awardReason) {
        this.awardReason = awardReason;
    }
}
