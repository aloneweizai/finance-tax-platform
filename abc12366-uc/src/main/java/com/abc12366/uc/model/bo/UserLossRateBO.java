package com.abc12366.uc.model.bo;

/**
 * 用户留存率统计
 *
 * @author lizhongwei
 * @create 2017-11-21 4:14 PM
 * @since 1.0.0
 */
public class UserLossRateBO {

    /**
     * 用户总数
     */
    private Integer userCount;

    /**
     * 流失用户总数
     */
    private Integer lossUserCount;

    /**
     * 流失率（流失用户总数/注册用户总数*100%）
     */
    private String rate;

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getLossUserCount() {
        return lossUserCount;
    }

    public void setLossUserCount(Integer lossUserCount) {
        this.lossUserCount = lossUserCount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
