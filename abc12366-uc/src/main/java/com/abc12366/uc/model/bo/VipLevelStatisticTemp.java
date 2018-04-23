package com.abc12366.uc.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-11-30
 * Time: 10:59
 */
public class VipLevelStatisticTemp {
    private float increase;
    private int allCount;
    private float lastIncrease;
    private float lastYearAll;

    public float getIncrease() {
        return increase;
    }

    public void setIncrease(float increase) {
        this.increase = increase;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public float getLastIncrease() {
        return lastIncrease;
    }

    public void setLastIncrease(float lastIncrease) {
        this.lastIncrease = lastIncrease;
    }

    public float getLastYearAll() {
        return lastYearAll;
    }

    public void setLastYearAll(float lastYearAll) {
        this.lastYearAll = lastYearAll;
    }

    @Override
    public String toString() {
        return "VipLevelStatisticTemp{" +
                "increase=" + increase +
                ", allCount=" + allCount +
                ", lastIncrease=" + lastIncrease +
                ", lastYearAll=" + lastYearAll +
                '}';
    }
}
