package com.abc12366.uc.model.bo;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-20
 * Time: 21:53
 */
public class PointsBO {
    /**
     * 我的积分
     */
    private String myPoints;
    /**
     * 我本月获得的积分
     */
    private String income;
    /**
     * 我本月花费的积分
     */
    private String outgo;

    public PointsBO() {
    }

    public String getMyPoints() {
        return myPoints;
    }

    public void setMyPoints(String myPoints) {
        this.myPoints = myPoints;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getOutgo() {
        return outgo;
    }

    public void setOutgo(String outgo) {
        this.outgo = outgo;
    }

    @Override
    public String toString() {
        return "PointsBO{" +
                "myPoints='" + myPoints + '\'' +
                ", income='" + income + '\'' +
                ", outgo='" + outgo + '\'' +
                '}';
    }
}
