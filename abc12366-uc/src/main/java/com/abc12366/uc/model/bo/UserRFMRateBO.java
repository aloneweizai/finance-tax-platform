package com.abc12366.uc.model.bo;

/**
 * 用户消费能力分析
 *
 * @author lizhongwei
 * @create 2017-11-21 4:14 PM
 * @since 1.0.0
 */
public class UserRFMRateBO {

    /**
     * 最近一次消费间隔统计时间段截止日期的天数
     */
    private Integer recency;

    /**
     * 统计时间段内消费次数
     */
    private Integer frequency;

    /**
     * 表示统计时间段内容消费总金额
     */
    private Double monetary;

    public Integer getRecency() {
        return recency;
    }

    public void setRecency(Integer recency) {
        this.recency = recency;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Double getMonetary() {
        return monetary;
    }

    public void setMonetary(Double monetary) {
        this.monetary = monetary;
    }
}
