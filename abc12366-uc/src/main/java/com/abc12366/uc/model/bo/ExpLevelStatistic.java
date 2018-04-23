package com.abc12366.uc.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-11-30
 * Time: 11:16
 */
public class ExpLevelStatistic {

    private Integer minValue;

    private Integer maxValue;

    /**用户等级**/
    private String levelCode;

    /**用户等级名称**/
    private String levelName;

    /**当前级别用户总数**/
    private int all;

    /**用户等级提升率**/
    private String increasePercent;

    /**今年当前级别总数**/
    private float thisYearIncrease;

    /**上一年用户等级数据**/
    private float lastYearAll;

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

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public float getThisYearIncrease() {
        return thisYearIncrease;
    }

    public void setThisYearIncrease(float thisYearIncrease) {
        this.thisYearIncrease = thisYearIncrease;
    }

    public float getLastYearAll() {
        return lastYearAll;
    }

    public void setLastYearAll(float lastYearAll) {
        this.lastYearAll = lastYearAll;
    }

    @Override
    public String toString() {
        return "ExpLevelStatistic{" +
                "levelCode='" + levelCode + '\'' +
                ", levelName='" + levelName + '\'' +
                ", all=" + all +
                ", increasePercent='" + increasePercent + '\'' +
                ", thisYearIncrease=" + thisYearIncrease +
                ", lastYearAll=" + lastYearAll +
                '}';
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }
}
