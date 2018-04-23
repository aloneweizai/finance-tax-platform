package com.abc12366.bangbang.model.question.bo;

import java.util.Date;

/**
 * @Author liuQi
 * @Date 2017/10/28 13:56
 * 帮派奖励积分设置
 */
public class QuestionFactionRewardSettingBo {

    /*时间月份*/
    private String date;

    /*id*/
    private String factionId;

    /*帮派名称*/
    private String factionName;

    /*帮主名称*/
    private String leaderName;

    /*帮派积分*/
    private String totalPoints;

    /*分配积分*/
    private String rewardsPoints;

    /*帮派荣誉值*/
    private String honor;

    /*分类名称*/
    private String classifyName;

    /*帮派人数*/
    private String memberCnt;

    /*分配状态*/
    private Boolean status;

    /*分配时间*/
    private Date rewardDate;

    public String getDate() {
        return date;
    }

    public QuestionFactionRewardSettingBo setDate(String date) {
        this.date = date;
        return this;
    }

    public String getFactionId() {
        return factionId;
    }

    public QuestionFactionRewardSettingBo setFactionId(String factionId) {
        this.factionId = factionId;
        return this;
    }

    public String getFactionName() {
        return factionName;
    }

    public QuestionFactionRewardSettingBo setFactionName(String factionName) {
        this.factionName = factionName;
        return this;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public QuestionFactionRewardSettingBo setLeaderName(String leaderName) {
        this.leaderName = leaderName;
        return this;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public QuestionFactionRewardSettingBo setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
        return this;
    }

    public String getRewardsPoints() {
        return rewardsPoints;
    }

    public QuestionFactionRewardSettingBo setRewardsPoints(String rewardsPoints) {
        this.rewardsPoints = rewardsPoints;
        return this;
    }

    public String getHonor() {
        return honor;
    }

    public QuestionFactionRewardSettingBo setHonor(String honor) {
        this.honor = honor;
        return this;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public QuestionFactionRewardSettingBo setClassifyName(String classifyName) {
        this.classifyName = classifyName;
        return this;
    }

    public String getMemberCnt() {
        return memberCnt;
    }

    public QuestionFactionRewardSettingBo setMemberCnt(String memberCnt) {
        this.memberCnt = memberCnt;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public QuestionFactionRewardSettingBo setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public Date getRewardDate() {
        return rewardDate;
    }

    public QuestionFactionRewardSettingBo setRewardDate(Date rewardDate) {
        this.rewardDate = rewardDate;
        return this;
    }
}
