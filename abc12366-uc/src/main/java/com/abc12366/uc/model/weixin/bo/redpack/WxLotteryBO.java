package com.abc12366.uc.model.weixin.bo.redpack;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-14 4:30 PM
 * @since 1.0.0
 */
public class WxLotteryBO {

    @NotEmpty
    @Length(min = 16, max = 64)
    private String activityId;

    @NotEmpty
    @Length(min = 2, max = 32)
    private String secret;

    @NotEmpty
    @Length(min = 16, max = 64)
    private String openId;

    @Length(min = 32, max = 64)
    private String userId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "WxLotteryBO{" +
                "activityId='" + activityId + '\'' +
                ", secret='" + secret + '\'' +
                ", openId='" + openId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
