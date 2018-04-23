package com.abc12366.uc.model.order;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 用户换货
 **/
@SuppressWarnings("serial")
public class OrderExchange implements Serializable {

    /**
     * PK
     **/
    private String id;

    /**
     * FK
     **/
    private String userId;

    /**
     * 订单号
     **/
    private String orderNo;

    /**
     * 换货原因
     **/
    private String reason;

    /**
     * 用户备注
     **/
    private String userRemark;

    /**
     * 管理员备注
     **/
    private String adminRemark;

    /**
     * 用户换货快递单号
     **/
    private String expressNo;

    /**
     * 用户换货快递公司
     **/
    private String expressComp;

    /**
     * 用户换货，寄送的快递单号
    **/
    private String toExpressNo;

    /**
     * 用户换货，寄送的快递公司ID
     **/
    private String toExpressComp;

    /**
     * 用户换货，寄送的快递公司名称
     **/
    private String toExpressCompName;
    /**
     * 退单状态
     **/
    private String status;

    /**
     * 管理员确认退单备注
     */
    private String adminConfirmRemark;

    /**收件人**/
    private String consignee;

    /**联系电话**/
    private String contactNumber;

    /**收件地址**/
    private String shippingAddress;

    /**退款备注**/
    private String refundRemark;

    /****/
    private Timestamp createTime;

    /****/
    private Timestamp lastUpdate;

    // 服务类型 1-换货 2-退货
    private String type;
    // 产品名称
    private String name;
    // 产品图片
    private String imageUrl;
    // 商品类型
    private String goodsType;
    // 用户名
    private String username;
    // 下单时间
    private Timestamp orderTime;
    // 商品数量
    private Integer num;

    public OrderExchange() {
    }

    public OrderExchange(String id, String userId, String orderNo, String reason, String userRemark, String
            adminRemark, String expressNo, String expressComp, String toExpressNo, String status, Timestamp
            createTime, Timestamp lastUpdate, String type, String name, String username, Timestamp orderTime, Integer
            num, String imageUrl, String goodsType) {
        this.id = id;
        this.userId = userId;
        this.orderNo = orderNo;
        this.reason = reason;
        this.userRemark = userRemark;
        this.adminRemark = adminRemark;
        this.expressNo = expressNo;
        this.expressComp = expressComp;
        this.toExpressNo = toExpressNo;
        this.status = status;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
        this.type = type;
        this.name = name;
        this.username = username;
        this.orderTime = orderTime;
        this.num = num;
        this.imageUrl = imageUrl;
        this.goodsType = goodsType;
    }

    private OrderExchange(Builder builder) {
        setId(builder.id);
        setUserId(builder.userId);
        setOrderNo(builder.orderNo);
        setReason(builder.reason);
        setUserRemark(builder.userRemark);
        setAdminRemark(builder.adminRemark);
        setExpressNo(builder.expressNo);
        setExpressComp(builder.expressComp);
        setToExpressNo(builder.toExpressNo);
        setStatus(builder.status);
        setCreateTime(builder.createTime);
        setLastUpdate(builder.lastUpdate);
        setType(builder.type);
        setName(builder.name);
        setUsername(builder.username);
        setOrderTime(builder.orderTime);
        setNum(builder.num);
        setImageUrl(builder.imageUrl);
        setGoodsType(builder.goodsType);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getAdminRemark() {
        return adminRemark;
    }

    public void setAdminRemark(String adminRemark) {
        this.adminRemark = adminRemark;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressComp() {
        return expressComp;
    }

    public void setExpressComp(String expressComp) {
        this.expressComp = expressComp;
    }

    public String getToExpressNo() {
        return toExpressNo;
    }

    public void setToExpressNo(String toExpressNo) {
        this.toExpressNo = toExpressNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    @Override
    public String toString() {
        return "OrderExchange{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", reason='" + reason + '\'' +
                ", userRemark='" + userRemark + '\'' +
                ", adminRemark='" + adminRemark + '\'' +
                ", expressNo='" + expressNo + '\'' +
                ", expressComp='" + expressComp + '\'' +
                ", toExpressNo='" + toExpressNo + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", orderTime=" + orderTime +
                ", num=" + num +
                '}';
    }

    public String getAdminConfirmRemark() {
        return adminConfirmRemark;
    }

    public void setAdminConfirmRemark(String adminConfirmRemark) {
        this.adminConfirmRemark = adminConfirmRemark;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getRefundRemark() {
        return refundRemark;
    }

    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark;
    }

    public String getToExpressComp() {
        return toExpressComp;
    }

    public void setToExpressComp(String toExpressComp) {
        this.toExpressComp = toExpressComp;
    }

    public String getToExpressCompName() {
        return toExpressCompName;
    }

    public void setToExpressCompName(String toExpressCompName) {
        this.toExpressCompName = toExpressCompName;
    }

    public static final class Builder {
        private String id;
        private String userId;
        private String orderNo;
        private String reason;
        private String userRemark;
        private String adminRemark;
        private String expressNo;
        private String expressComp;
        private String toExpressNo;
        private String status;
        private Timestamp createTime;
        private Timestamp lastUpdate;
        private String type;
        private String name;
        private String username;
        private Timestamp orderTime;
        private Integer num;
        private String imageUrl;
        private String goodsType;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public Builder orderNo(String val) {
            orderNo = val;
            return this;
        }

        public Builder reason(String val) {
            reason = val;
            return this;
        }

        public Builder userRemark(String val) {
            userRemark = val;
            return this;
        }

        public Builder adminRemark(String val) {
            adminRemark = val;
            return this;
        }

        public Builder expressNo(String val) {
            expressNo = val;
            return this;
        }

        public Builder expressComp(String val) {
            expressComp = val;
            return this;
        }

        public Builder toExpressNo(String val) {
            toExpressNo = val;
            return this;
        }

        public Builder status(String val) {
            status = val;
            return this;
        }

        public Builder createTime(Timestamp val) {
            createTime = val;
            return this;
        }

        public Builder lastUpdate(Timestamp val) {
            lastUpdate = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder orderTime(Timestamp val) {
            orderTime = val;
            return this;
        }

        public Builder num(Integer val) {
            num = val;
            return this;
        }

        public Builder imageUrl(String val) {
            imageUrl = val;
            return this;
        }

        public Builder goodsType(String val) {
            goodsType = val;
            return this;
        }

        public OrderExchange build() {
            return new OrderExchange(this);
        }
    }
}
