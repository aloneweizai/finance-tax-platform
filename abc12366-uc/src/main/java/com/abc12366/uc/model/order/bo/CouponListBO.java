package com.abc12366.uc.model.order.bo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 优惠劵查询列表对象
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2018-01-13 11:58 AM
 * @since 1.0.0
 */
public class CouponListBO {

    /**
     * 优惠劵ID
     */
    private String id;

    /**
     * 优惠劵名称
     */
    private String couponName;

    /**
     * 优惠劵模式：固定-FIXED, 浮动-FLOAT
     */
    private String couponMode;

    /**
     * 优惠类型：满减-MANJIAN，立减-LIJIAN，折扣-ZHEKOU
     */
    private String couponType;

    /**
     * 计算金额类型：订单金额-ORDER, 邮费金额-POSTAGE
     */
    private String amountType;

    /**
     * 有效期类型：固定时间段-PERIOD，固定天数-DAYS
     */
    private String validType;

    /**
     * 有效期起
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validStartTime;

    /**
     * 有效期止
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validEndTime;

    /**
     * 有效天数
     */
    private Integer validDays;

    /**
     * 状态: 0-删除 1-草稿 2-启用 3-停用
     */
    private String status;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdate;

    /**
     * 是否在有效期内
     */
    private Boolean valid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponMode() {
        return couponMode;
    }

    public void setCouponMode(String couponMode) {
        this.couponMode = couponMode;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public String getValidType() {
        return validType;
    }

    public void setValidType(String validType) {
        this.validType = validType;
    }

    public Date getValidStartTime() {
        return validStartTime;
    }

    public void setValidStartTime(Date validStartTime) {
        this.validStartTime = validStartTime;
    }

    public Date getValidEndTime() {
        return validEndTime;
    }

    public void setValidEndTime(Date validEndTime) {
        this.validEndTime = validEndTime;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Boolean getValid() {
        Date now = new Date();
        if(validStartTime != null && validEndTime != null){
            return now.after(validStartTime) && now.before(validEndTime);
        }
        return true;
    }

    @Override
    public String toString() {
        return "CouponListBO{" +
                "id='" + id + '\'' +
                ", couponName='" + couponName + '\'' +
                ", couponMode='" + couponMode + '\'' +
                ", couponType='" + couponType + '\'' +
                ", amountType='" + amountType + '\'' +
                ", validType='" + validType + '\'' +
                ", validStartTime=" + validStartTime +
                ", validEndTime=" + validEndTime +
                ", validDays=" + validDays +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                ", valid=" + valid +
                '}';
    }

}
