package com.abc12366.uc.model.bo;

import java.util.Date;

/**
 * 经验值日志实体类
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 15:05
 */
public class ExperienceLogBO {
    //经验值日志ID，新增时为空
    private String id;

    //用户ID
    private String userId;

    //经验值规则ID
    private String ruleId;

    //增加的经验值
    private int income;

    //减少的经验值
    private int outgo;

    //当前经验值
    private int usableExp;

    //创建时间，新增时不传
    private Date createTime;

    public ExperienceLogBO() {
    }

    public ExperienceLogBO(String id, String userId, String ruleId, int income, int outgo, int usableExp, Date
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
        return "ExperienceLogBO{" +
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
