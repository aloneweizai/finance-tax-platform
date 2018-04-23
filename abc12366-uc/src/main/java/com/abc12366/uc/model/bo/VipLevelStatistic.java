package com.abc12366.uc.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-11-30
 * Time: 11:16
 */
public class VipLevelStatistic {
    private String levelCode;
    private String levelName;
    private int all;
    private int increase;
    private String increasePercent;
    private int lastYearAll;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public String getIncreasePercent() {
        return increasePercent;
    }

    public void setIncreasePercent(String increasePercent) {
        this.increasePercent = increasePercent;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public int getIncrease() {
        return increase;
    }

    public void setIncrease(int increase) {
        this.increase = increase;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getLastYearAll() {
        return lastYearAll;
    }

    public void setLastYearAll(int lastYearAll) {
        this.lastYearAll = lastYearAll;
    }

    @Override
    public String toString() {
        return "VipLevelStatistic{" +
                "levelCode='" + levelCode + '\'' +
                ", levelName='" + levelName + '\'' +
                ", all=" + all +
                ", increase=" + increase +
                ", increasePercent='" + increasePercent + '\'' +
                ", lastYearAll=" + lastYearAll +
                '}';
    }
}
