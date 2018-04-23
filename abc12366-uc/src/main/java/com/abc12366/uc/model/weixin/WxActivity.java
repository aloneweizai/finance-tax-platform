package com.abc12366.uc.model.weixin;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 微信红包活动
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-14 10:39 AM
 * @since 1.0.0
 */
public class WxActivity {

    // 活动编号（系统产生）
    private String id;
    // 活动名称
    @NotEmpty
    @Length(min = 2, max = 16)
    private String name;
    // 活动描述
    @Length(max = 500)
    private String description;
    // 活动起始时间
    @NotNull
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    // 活动截止时间
    @NotNull
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    // 规则类型
    @NotEmpty
    @Length(min = 1, max = 1)
    private String ruleType;
    // 生成规则定义
    @Length(max = 20)
    private String rule;
    // 固定金额／随机金额
    @NotEmpty
    @Length(min = 1, max = 1)
    private String amountType;

    /**
     * 随机金额-最小金额
     */
    @Min(1)
    private Double minAmount;

    // 随机最大金额/固定金额
    @NotNull
    @Min(1)
    private Double amount;
    // 中奖概率（1%-100%）
    @NotEmpty
    @Length(min = 2, max = 10)
    private String probability;
    // 活动启动/停止状态（启动状态时平台才产生口令）
    @NotNull
    private Boolean status;
    // 红包祝福语
    @NotEmpty
    @Length(max = 16)
    private String wishing;
    // 红包备注
    @NotEmpty
    @Length(max = 16)
    private String remark;
    // 红包数量
    @NotNull
    private Integer num;
    // 每天抽奖次数限制
    @NotNull
    private Integer times;
    // 创建时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    // 修改时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdate;

    /**
     * 排序
     */
    @Max(255)
    private Integer sort;

    /**
     * 已发送人数
     */
    private Integer sent;

    /**
     * 已发送金额
     */
    private Double sentAmount;

    /**
     * 已领取人数
     */
    private Integer received;

    /**
     * 已领取金额
     */
    private Double receivedAmount;

    /**
     * 活动参与人数
     */
    private Integer nop;

    /**
     * 活动是否过期
     */
    private Boolean outdated;

    /**
     * 是否赠送积分
     */
    @NotNull
    private Boolean giftPoints;

    /**
     * 赠送积分
     */
    private Integer points;

    /**
     * 是否赠送优惠劵
     */
    @NotNull
    private Boolean giftCoupon;

    /**
     * 优惠劵活动ID
     */
    @Length(min = 32, max = 64)
    private String activityId;

    public WxActivity() {
    }

    public WxActivity(String id, String name, String description, Date startTime, Date endTime, String ruleType,
                      String rule, String amountType, Double amount, String probability, Boolean status, String
                              wishing, String remark, Integer num, Integer times, Date createTime, Date lastUpdate,
                      Integer sort, Boolean giftPoints, Boolean giftCoupon, String activityId, Double minAmount,
                      Integer points) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ruleType = ruleType;
        this.rule = rule;
        this.amountType = amountType;
        this.amount = amount;
        this.probability = probability;
        this.status = status;
        this.wishing = wishing;
        this.remark = remark;
        this.num = num;
        this.times = times;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
        this.sort = sort;
        this.giftPoints = giftPoints;
        this.giftCoupon = giftCoupon;
        this.activityId = activityId;
        this.minAmount = minAmount;
        this.points = points;
    }

    private WxActivity(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setDescription(builder.description);
        setStartTime(builder.startTime);
        setEndTime(builder.endTime);
        setRuleType(builder.ruleType);
        setRule(builder.rule);
        setAmountType(builder.amountType);
        setMinAmount(builder.minAmount);
        setAmount(builder.amount);
        setProbability(builder.probability);
        setStatus(builder.status);
        setWishing(builder.wishing);
        setRemark(builder.remark);
        setNum(builder.num);
        setTimes(builder.times);
        setCreateTime(builder.createTime);
        setLastUpdate(builder.lastUpdate);
        setSort(builder.sort);
        setGiftPoints(builder.giftPoints);
        setGiftCoupon(builder.giftCoupon);
        setActivityId(builder.activityId);
        setPoints(builder.points);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
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

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSent() {
        return sent;
    }

    public void setSent(Integer sent) {
        this.sent = sent;
    }

    public Double getSentAmount() {
        return sentAmount;
    }

    public void setSentAmount(Double sentAmount) {
        this.sentAmount = sentAmount;
    }

    public Integer getReceived() {
        return received;
    }

    public void setReceived(Integer received) {
        this.received = received;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Integer getNop() {
        return nop;
    }

    public void setNop(Integer nop) {
        this.nop = nop;
    }

    public Boolean getOutdated() {
        Date now = new Date();
        return now.before(startTime) || now.after(endTime);
    }

    public Boolean getGiftPoints() {
        return giftPoints;
    }

    public void setGiftPoints(Boolean giftPoints) {
        this.giftPoints = giftPoints;
    }

    public Boolean getGiftCoupon() {
        return giftCoupon;
    }

    public void setGiftCoupon(Boolean giftCoupon) {
        this.giftCoupon = giftCoupon;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "WxActivity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", ruleType='" + ruleType + '\'' +
                ", rule='" + rule + '\'' +
                ", amountType='" + amountType + '\'' +
                ", minAmount=" + minAmount +
                ", amount=" + amount +
                ", probability='" + probability + '\'' +
                ", status=" + status +
                ", wishing='" + wishing + '\'' +
                ", remark='" + remark + '\'' +
                ", num=" + num +
                ", times=" + times +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                ", sort=" + sort +
                ", sent=" + sent +
                ", sentAmount=" + sentAmount +
                ", received=" + received +
                ", receivedAmount=" + receivedAmount +
                ", nop=" + nop +
                ", outdated=" + outdated +
                ", giftPoints=" + giftPoints +
                ", giftCoupon=" + giftCoupon +
                ", activityId='" + activityId + '\'' +
                ", points='" + points + '\'' +
                '}';
    }

    public static final class Builder {
        private String id;
        private String name;
        private String description;
        private Date startTime;
        private Date endTime;
        private String ruleType;
        private String rule;
        private String amountType;
        private Double minAmount;
        private Double amount;
        private String probability;
        private Boolean status;
        private String wishing;
        private String remark;
        private Integer num;
        private Integer times;
        private Date createTime;
        private Date lastUpdate;
        private Integer sort;
        private Boolean giftPoints;
        private Boolean giftCoupon;
        private String activityId;
        private Integer points;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
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

        public Builder ruleType(String val) {
            ruleType = val;
            return this;
        }

        public Builder rule(String val) {
            rule = val;
            return this;
        }

        public Builder amountType(String val) {
            amountType = val;
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

        public Builder probability(String val) {
            probability = val;
            return this;
        }

        public Builder status(Boolean val) {
            status = val;
            return this;
        }

        public Builder wishing(String val) {
            wishing = val;
            return this;
        }

        public Builder remark(String val) {
            remark = val;
            return this;
        }

        public Builder num(Integer val) {
            num = val;
            return this;
        }

        public Builder times(Integer val) {
            times = val;
            return this;
        }

        public Builder createTime(Date val) {
            createTime = val;
            return this;
        }

        public Builder lastUpdate(Date val) {
            lastUpdate = val;
            return this;
        }

        public Builder sort(Integer val) {
            sort = val;
            return this;
        }

        public Builder giftPoints(Boolean val) {
            giftPoints = val;
            return this;
        }

        public Builder giftCoupon(Boolean val) {
            giftCoupon = val;
            return this;
        }

        public Builder activityId(String val) {
            activityId = val;
            return this;
        }

        public Builder points(Integer val) {
            points = val;
            return this;
        }

        public WxActivity build() {
            return new WxActivity(this);
        }
    }
}
