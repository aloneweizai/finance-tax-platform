package com.abc12366.uc.model.bo;

/**
 * 用户消费能力分析
 *
 * @author lizhongwei
 * @create 2017-11-21 4:14 PM
 * @since 1.0.0
 */
public class UserRFMBO {

    /**
     *R表示最近一次消费间隔统计时间段截止日期的天数
     */
    private Integer minTime;

    private Integer maxTime;

    private Double avgTime;
    /**
     * F表示统计时间段内消费次数，
     */
    private Integer minConsum;

    private Integer maxConsum;

    private Double avgConsum;

    /**
     * M表示统计时间段内容消费总金额
     */
    private Double minPrice;

    private Double maxPrice;

    private Double avgPrice;

    public Integer getMinTime() {
        return minTime;
    }

    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
    }

    public Integer getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Integer maxTime) {
        this.maxTime = maxTime;
    }

    public Double getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Double avgTime) {
        this.avgTime = avgTime;
    }

    public Integer getMinConsum() {
        return minConsum;
    }

    public void setMinConsum(Integer minConsum) {
        this.minConsum = minConsum;
    }

    public Integer getMaxConsum() {
        return maxConsum;
    }

    public void setMaxConsum(Integer maxConsum) {
        this.maxConsum = maxConsum;
    }

    public Double getAvgConsum() {
        return avgConsum;
    }

    public void setAvgConsum(Double avgConsum) {
        this.avgConsum = avgConsum;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }
}
