package com.abc12366.uc.model.bo;

import java.util.List;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-14
 * Time: 17:21
 */
public class PointAnalysisBO {
    /**
     * 积分总收入
     */
    private float sumIncome;
    /**
     * 积分总消费
     */
    private float sumOutgo;
    /**
     * 收支比：收支比=（消费积分-产生积分）/产生积分*100%
     */
    private String outInPercent;
    private List<PointRuleAnalysisBO> pointRuleAnalysisBOList;
    private int total;

    public float getSumIncome() {
        return sumIncome;
    }

    public void setSumIncome(float sumIncome) {
        this.sumIncome = sumIncome;
    }

    public float getSumOutgo() {
        return sumOutgo;
    }

    public void setSumOutgo(float sumOutgo) {
        this.sumOutgo = sumOutgo;
    }

    public String getOutInPercent() {
        return outInPercent;
    }

    public void setOutInPercent(String outInPercent) {
        this.outInPercent = outInPercent;
    }

    public List<PointRuleAnalysisBO> getPointRuleAnalysisBOList() {
        return pointRuleAnalysisBOList;
    }

    public void setPointRuleAnalysisBOList(List<PointRuleAnalysisBO> pointRuleAnalysisBOList) {
        this.pointRuleAnalysisBOList = pointRuleAnalysisBOList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PointAnalysisBO{" +
                "sumIncome=" + sumIncome +
                ", sumOutgo=" + sumOutgo +
                ", outInPercent='" + outInPercent + '\'' +
                ", pointRuleAnalysisBOList=" + pointRuleAnalysisBOList +
                ", total=" + total +
                '}';
    }
}
