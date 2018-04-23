package com.abc12366.uc.model.order.bo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 优惠劵新增、修改对象
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2018-01-13 12:10 PM
 * @since 1.0.0
 */
public class CouponBO {

    /**
     * 优惠劵ID
     */
    @Length(max = 32)
    private String id;

    /**
     * 优惠劵名称
     */
    @NotEmpty
    @Length(min = 2, max = 20)
    private String couponName;

    /**
     * 优惠劵模式：固定-FIXED, 浮动-FLOAT
     */
    @NotEmpty
    @Length(max = 5)
    private String couponMode;

    /**
     * 优惠类型：满减-MANJIAN，立减-LIJIAN，折扣-ZHEKOU
     */
    @NotEmpty
    @Length(max = 10)
    private String couponType;

    /**
     * 满多少金额
     */
    private Double param1;

    /**
     * 减多少金额、折扣，开始金额
     */
    private Double param2;

    /**
     * 减多少金额、折扣，结束金额
     */
    private Double param3;

    /**
     * 计算金额类型：订单金额-ORDER, 邮费金额-POSTAGE
     */
    @NotEmpty
    @Length(max = 10)
    private String amountType;

    /**
     * 有效期类型：固定时间段-PERIOD，固定天数-DAYS
     */
    @NotEmpty
    @Length(max = 10)
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
     * 描述
     */
    @Length(max = 200)
    private String description;

    /**
     * 状态: 0-删除 1-草稿 2-启用 3-停用
     */
    @Length(max = 1)
    private String status;

    /**
     * 商品品类ID，逗号分隔
     */
    @NotEmpty
//    @Size(max = 10)
    private String categoryIds;

    /**
     * 优惠券活动图片
     */
    private String imageUrl;

    /**
     * 已领取数量
     */
    private Integer collectNum;

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

    public Double getParam1() {
        return param1;
    }

    public void setParam1(Double param1) {
        this.param1 = param1;
    }

    public Double getParam2() {
        return param2;
    }

    public void setParam2(Double param2) {
        this.param2 = param2;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    @Override
    public String toString() {
        return "CouponBO{" +
                "id='" + id + '\'' +
                ", couponName='" + couponName + '\'' +
                ", couponMode='" + couponMode + '\'' +
                ", couponType='" + couponType + '\'' +
                ", param1=" + param1 +
                ", param2=" + param2 +
                ", amountType='" + amountType + '\'' +
                ", validType='" + validType + '\'' +
                ", validStartTime=" + validStartTime +
                ", validEndTime=" + validEndTime +
                ", validDays=" + validDays +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", categoryIds=" + categoryIds +
                '}';
    }

    public Double getParam3() {
        return param3;
    }

    public void setParam3(Double param3) {
        this.param3 = param3;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }
}
