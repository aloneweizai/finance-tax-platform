package com.abc12366.uc.model.bo;

import java.util.Date;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-14
 * Time: 17:21
 */
public class PointRuleinfoBO {
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 积分规则名称
     */
    private String ruleName;
    /**
     * 收入
     */
    private long income;
    /**
     * 支出
     */
    private long outgo;
    /**
     * 日志记录时间
     */
    private Date createTime;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income = income;
    }

    public long getOutgo() {
        return outgo;
    }

    public void setOutgo(long outgo) {
        this.outgo = outgo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PointRuleinfoBO{" +
                "nickname='" + nickname + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", income=" + income +
                ", outgo=" + outgo +
                ", createTime=" + createTime +
                '}';
    }
}
