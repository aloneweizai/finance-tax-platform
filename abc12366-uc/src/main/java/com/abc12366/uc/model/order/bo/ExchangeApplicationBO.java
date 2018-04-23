package com.abc12366.uc.model.order.bo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 退换货申请BO
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-11 11:26 AM
 * @since 1.0.0
 */
public class ExchangeApplicationBO {

    // PK
    private String id;
    // 订单号
    @NotEmpty
    @Length(min = 16, max = 64)
    private String orderNo;
    // 换货原因
    @NotEmpty
    @Length(min = 1, max = 1)
    private String reason;
    // 用户备注
    @Length(max = 500)
    private String userRemark;
    // 服务类型 1-换货 2-退货
    @NotEmpty
    @Length(min = 1, max = 1)
    private String type;

    /**收件人**/
    private String consignee;

    /**联系电话**/
    private String contactNumber;

    /**收件地址**/
    private String shippingAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "ExchangeApplicationBO{" +
                "id='" + id + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", reason='" + reason + '\'' +
                ", userRemark='" + userRemark + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
