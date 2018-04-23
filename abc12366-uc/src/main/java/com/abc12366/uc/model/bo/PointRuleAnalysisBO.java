package com.abc12366.uc.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-14
 * Time: 17:21
 */
public class PointRuleAnalysisBO {
    /**
     * 积分规则id
     */
    private String ruleId;
    /**
     * 积分规则名称
     */
    private String ruleName;
    /**
     * 时间区间
     */
    private String timeInterval;
    /**
     * 收入
     */
    private long sumRuleIncome;
    /**
     * 支出
     */
    private long sumRuleOutgo;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public long getSumRuleIncome() {
        return sumRuleIncome;
    }

    public void setSumRuleIncome(long sumRuleIncome) {
        this.sumRuleIncome = sumRuleIncome;
    }

    public long getSumRuleOutgo() {
        return sumRuleOutgo;
    }

    public void setSumRuleOutgo(long sumRuleOutgo) {
        this.sumRuleOutgo = sumRuleOutgo;
    }

    @Override
    public String toString() {
        return "PointRuleAnalysisBO{" +
                "ruleId='" + ruleId + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", timeInterval='" + timeInterval + '\'' +
                ", sumRuleIncome=" + sumRuleIncome +
                ", sumRuleOutgo=" + sumRuleOutgo +
                '}';
    }
}
