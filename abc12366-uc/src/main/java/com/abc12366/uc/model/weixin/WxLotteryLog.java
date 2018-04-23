package com.abc12366.uc.model.weixin;

import java.util.Date;

/**
 * 微信红包抽奖记录
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-15 10:46 AM
 * @since 1.0.0
 */
public class WxLotteryLog {

    private String id;
    private String openId;
    private String activityId;
    private String secret;
    private Date createTime;

    public WxLotteryLog() {
    }

    public WxLotteryLog(String id, String openId, String activityId, String secret, Date createTime) {
        this.id = id;
        this.openId = openId;
        this.activityId = activityId;
        this.secret = secret;
        this.createTime = createTime;
    }

    private WxLotteryLog(Builder builder) {
        setId(builder.id);
        setOpenId(builder.openId);
        setActivityId(builder.activityId);
        setSecret(builder.secret);
        setCreateTime(builder.createTime);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WxLotteryLog{" +
                "id='" + id + '\'' +
                ", openId='" + openId + '\'' +
                ", activityId='" + activityId + '\'' +
                ", secret='" + secret + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public static final class Builder {
        private String id;
        private String openId;
        private String activityId;
        private String secret;
        private Date createTime;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder openId(String val) {
            openId = val;
            return this;
        }

        public Builder activityId(String val) {
            activityId = val;
            return this;
        }

        public Builder secret(String val) {
            secret = val;
            return this;
        }

        public Builder createTime(Date val) {
            createTime = val;
            return this;
        }

        public WxLotteryLog build() {
            return new WxLotteryLog(this);
        }
    }
}
