package com.abc12366.uc.model.weixin;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 红包信息表
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-14 10:54 AM
 * @since 1.0.0
 */
public class WxRedEnvelop {
    // （主键）
    private String id;
    // 红包口令
    @NotEmpty
    @Length(min = 2, max = 16)
    private String secret;
    // 口令产生时间
    @NotNull
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    // 红包活动编号（主键）
    @NotEmpty
    @Length(min = 16, max = 64)
    private String activityId;
    // 活动起始时间
    @NotNull
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    // 活动截止时间
    @NotNull
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 随机金额-最小金额
     */
    @Min(1)
    private Double minAmount;

    // 红包金额
    @NotNull
    @Min(1)
    private Double amount;
    // 红包发放模式（固定金额、随机金额）
    @NotEmpty
    @Length(min = 1, max = 1)
    private String amountType;
    // 中奖概率（1%-100%）
    @NotEmpty
    @Length(min = 2, max = 10)
    private String probability;

    /**
     * 备注微信返回信息
     */
    private String remark;

    /**
     *  红包中奖金额
     */
    private Double sendAmount;

    /**
     * 发送状态 0:已中奖未发送
     */
    private String sendStatus;

    /**
     * 二维码地址
     */
    private String url;

    /**
     * 业务ID
     */
    private String businessId;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 领取状态
     */
    private String receiveStatus;

    /**
     * 用户输入口令的时间
     */
    private Date receiveTime;

    /**
     * 用户IP
     */
    private String ip;

    /**
     * 用户OPENID
     */
    private String openId;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 商户订单号
     */
    private String billno;

    /**
     * 微信昵称
     */
    private String wxnickname;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户ID
     */
    private String userId;

    public WxRedEnvelop() {
    }

    public WxRedEnvelop(String id, String secret, Date createTime, String activityId, Date startTime, Date endTime,
                        Double amount, String amountType, String probability, Double sendAmount, String sendStatus,
                        String url, Date sendTime, String receiveStatus, Date receiveTime, String ip, String openId,
                        String remark, String billno, String businessId, Double minAmount, String userId) {
        this.id = id;
        this.secret = secret;
        this.createTime = createTime;
        this.activityId = activityId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.amount = amount;
        this.amountType = amountType;
        this.probability = probability;
        this.sendAmount = sendAmount;
        this.sendStatus = sendStatus;
        this.url = url;
        this.sendTime = sendTime;
        this.receiveStatus = receiveStatus;
        this.receiveTime = receiveTime;
        this.ip = ip;
        this.openId = openId;
        this.remark = remark;
        this.billno = billno;
        this.businessId = businessId;
        this.minAmount = minAmount;
        this.userId = userId;
    }

    private WxRedEnvelop(Builder builder) {
        setId(builder.id);
        setSecret(builder.secret);
        setCreateTime(builder.createTime);
        setActivityId(builder.activityId);
        setStartTime(builder.startTime);
        setEndTime(builder.endTime);
        setMinAmount(builder.minAmount);
        setAmount(builder.amount);
        setAmountType(builder.amountType);
        setProbability(builder.probability);
        setSendAmount(builder.sendAmount);
        setSendStatus(builder.sendStatus);
        setUrl(builder.url);
        setSendTime(builder.sendTime);
        setReceiveStatus(builder.receiveStatus);
        setReceiveTime(builder.receiveTime);
        setIp(builder.ip);
        setOpenId(builder.openId);
        setRemark(builder.remark);
        setBillno(builder.billno);
        setBusinessId(builder.businessId);
        setUserId(builder.userId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public Double getSendAmount() {
        return sendAmount;
    }

    public void setSendAmount(Double sendAmount) {
        this.sendAmount = sendAmount;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getWxnickname() {
        return wxnickname;
    }

    public void setWxnickname(String wxnickname) {
        this.wxnickname = wxnickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "WxRedEnvelop{" +
                "id='" + id + '\'' +
                ", secret='" + secret + '\'' +
                ", createTime=" + createTime +
                ", activityId='" + activityId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", minAmount=" + minAmount +
                ", amount=" + amount +
                ", amountType='" + amountType + '\'' +
                ", probability='" + probability + '\'' +
                ", remark='" + remark + '\'' +
                ", sendAmount=" + sendAmount +
                ", sendStatus='" + sendStatus + '\'' +
                ", url='" + url + '\'' +
                ", businessId='" + businessId + '\'' +
                ", sendTime=" + sendTime +
                ", receiveStatus='" + receiveStatus + '\'' +
                ", receiveTime=" + receiveTime +
                ", ip='" + ip + '\'' +
                ", openId='" + openId + '\'' +
                ", name='" + name + '\'' +
                ", billno='" + billno + '\'' +
                ", wxnickname='" + wxnickname + '\'' +
                ", phone='" + phone + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public static final class Builder {
        private String id;
        private String secret;
        private Date createTime;
        private String activityId;
        private Date startTime;
        private Date endTime;
        private Double minAmount;
        private Double amount;
        private String amountType;
        private String probability;
        private Double sendAmount;
        private String sendStatus;
        private String url;
        private String businessId;
        private Date sendTime;
        private String receiveStatus;
        private Date receiveTime;
        private String ip;
        private String openId;
        private String remark;
        private String billno;
        private String userId;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
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

        public Builder activityId(String val) {
            activityId = val;
            return this;
        }

        public Builder startTime(Date val) {
            startTime = val;
            return this;
        }

        public Builder endTime(Date val) {
            endTime = val;
            return this;
        }

        public Builder minAmount(Double val) {
            minAmount = val;
            return this;
        }

        public Builder amount(Double val) {
            amount = val;
            return this;
        }

        public Builder amountType(String val) {
            amountType = val;
            return this;
        }

        public Builder probability(String val) {
            probability = val;
            return this;
        }

        public Builder sendAmount(Double val) {
            sendAmount = val;
            return this;
        }

        public Builder sendStatus(String val) {
            sendStatus = val;
            return this;
        }

        public Builder sendTime(Date val) {
            sendTime = val;
            return this;
        }

        public Builder receiveStatus(String val) {
            receiveStatus = val;
            return this;
        }

        public Builder receiveTime(Date val) {
            receiveTime = val;
            return this;
        }

        public Builder ip(String val) {
            ip = val;
            return this;
        }

        public Builder openId(String val) {
            openId = val;
            return this;
        }

        public Builder remark(String val) {
            remark = val;
            return this;
        }

        public Builder billno(String val) {
            billno = val;
            return this;
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Builder businessId(String val) {
            businessId = val;
            return this;
        }

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public WxRedEnvelop build() {
            return new WxRedEnvelop(this);
        }
    }
}
