package com.abc12366.uc.model.order;

import java.io.Serializable;
import java.util.Date;


/**
 * 订单日志记录
 **/
@SuppressWarnings("serial")
public class OrderLog implements Serializable {

    /****/
    private String id;

    /**
     * 快递单号
     **/
    private String orderNo;

    /**
     * 动作
     **/
    private String action;

    /**
     * 创建时间
     **/
    private Date createTime;

    /**
     * 创建用户
     **/
    private String createUser;

    /**
     * 备注
     **/
    private String remark;

    /**订单日志类型，0：订购日志，1：退单日志**/
    private String logType;

    /**退换货信息ID**/
    private String exchangeId;



    public OrderLog() {
    }

    private OrderLog(Builder builder) {
        setId(builder.id);
        setOrderNo(builder.orderNo);
        setAction(builder.action);
        setCreateTime(builder.createTime);
        setCreateUser(builder.createUser);
        setRemark(builder.remark);
        setLogType(builder.logType);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }


    public static final class Builder {
        private String id;
        private String orderNo;
        private String action;
        private Date createTime;
        private String createUser;
        private String remark;
        private String logType;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder orderNo(String val) {
            orderNo = val;
            return this;
        }

        public Builder action(String val) {
            action = val;
            return this;
        }

        public Builder createTime(Date val) {
            createTime = val;
            return this;
        }

        public Builder createUser(String val) {
            createUser = val;
            return this;
        }

        public Builder remark(String val) {
            remark = val;
            return this;
        }

        public Builder logType(String val) {
            logType = val;
            return this;
        }

        public OrderLog build() {
            return new OrderLog(this);
        }
    }
}
