package com.abc12366.uc.model.bo;

/**
 * 用户留存统计实体类
 *
 * @author lizhongwei
 * @create 2017-11-21 4:14 PM
 * @since 1.0.0
 */
public class UserRetainedRateBO {

    /**
     * 当月注册用户总数
     */
    private Integer userCount;

    /**
     * 留存用户总数
     */
    private Integer rateCount;

    /**
     * 留存率（留存用户总数/注册用户总数*100%）
     */
    private String rate;

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Integer getRateCount() {
        return rateCount;
    }

    public void setRateCount(Integer rateCount) {
        this.rateCount = rateCount;
    }
}
