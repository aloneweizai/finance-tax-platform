package com.abc12366.uc.model.weixin.bo.redpack;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-23 5:23 PM
 * @since 1.0.0
 */
public class SentReceived {

    private String activityId;

    // 已发送人数
    private Integer sent;

    // 已发送金额
    private Double sentAmount;

    // 已领取人数
    private Integer received;

    // 已领取金额
    private Double receivedAmount;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Integer getSent() {
        return sent;
    }

    public void setSent(Integer sent) {
        this.sent = sent;
    }

    public Integer getReceived() {
        return received;
    }

    public void setReceived(Integer received) {
        this.received = received;
    }

    public Double getSentAmount() {
        return sentAmount;
    }

    public void setSentAmount(Double sentAmount) {
        this.sentAmount = sentAmount;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }
}
