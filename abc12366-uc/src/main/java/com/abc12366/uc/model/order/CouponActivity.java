package com.abc12366.uc.model.order;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 优惠劵活动管理
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2018-01-13 11:40 AM
 * @since 1.0.0
 */
public class CouponActivity {

    /**
     * 优惠劵活动ID
     */
    private String id;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动链接
     */
    private String activityLink;

    /**
     * 优惠劵ID
     */
    private String couponId;

    /**
     * 活动开始时间
     */
    private Date activityStartTime;

    /**
     * 活动截止时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityEndTime;

    /**
     * 优惠劵发放数量
     */
    private Integer couponNum;

    /**
     * 限制类型：true-限制，false-不限制
     */
    private Boolean limit;

    /**
     * 每人限制领取数量
     */
    private Integer limitNum;

    /**
     * 领取方式：系统派发-SYSTEM，用户领取-USER
     */
    private String getType;

    /**
     * 校验接口
     */
    private String validApi;

    /**
     * 是否校验接口
     */
    private Boolean valid;

    /**
     * 目标人群：1-全部用户，2-部分用户，3-特定用户
     */
    private String target;

    /**
     * 区域操作符：等于(equals), 不等于(ne)
     */
    private String areaOper;

    /**
     * 区域ID，逗号分隔
     */
    private String areaIds;

    /**
     * 标签操作符：等于(equals), 不等于(ne)
     */
    private String tagOper;

    /**
     * 标签ID，逗号分隔
     */
    private String tagIds;

    /**
     * 注册时间操作符：小于等于(lte)，大于等于(gte)，时间段(between)
     */
    private String regTimeOper;

    /**
     * 注册开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regStartTime;

    /**
     * 注册结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regEndTime;

    /**
     * 会员类型，逗号分隔
     */
    private String vips;

    /**
     * 特定用户：用户ID，逗号分隔
     */
    private String userIds;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 活动图片
     */
    private String imageUrl;

    /**
     * 活动状态: 0-删除 1-草稿 2-启用 3-停用
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityLink() {
        return activityLink;
    }

    public void setActivityLink(String activityLink) {
        this.activityLink = activityLink;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public Integer getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(Integer couponNum) {
        this.couponNum = couponNum;
    }

    public Boolean getLimit() {
        return limit;
    }

    public void setLimit(Boolean limit) {
        this.limit = limit;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public String getGetType() {
        return getType;
    }

    public void setGetType(String getType) {
        this.getType = getType;
    }

    public String getValidApi() {
        return validApi;
    }

    public void setValidApi(String validApi) {
        this.validApi = validApi;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAreaOper() {
        return areaOper;
    }

    public void setAreaOper(String areaOper) {
        this.areaOper = areaOper;
    }

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
    }

    public String getTagOper() {
        return tagOper;
    }

    public void setTagOper(String tagOper) {
        this.tagOper = tagOper;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getRegTimeOper() {
        return regTimeOper;
    }

    public void setRegTimeOper(String regTimeOper) {
        this.regTimeOper = regTimeOper;
    }

    public Date getRegStartTime() {
        return regStartTime;
    }

    public void setRegStartTime(Date regStartTime) {
        this.regStartTime = regStartTime;
    }

    public Date getRegEndTime() {
        return regEndTime;
    }

    public void setRegEndTime(Date regEndTime) {
        this.regEndTime = regEndTime;
    }

    public String getVips() {
        return vips;
    }

    public void setVips(String vips) {
        this.vips = vips;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    @Override
    public String toString() {
        return "CouponActivity{" +
                "id='" + id + '\'' +
                ", activityName='" + activityName + '\'' +
                ", activityLink='" + activityLink + '\'' +
                ", couponId='" + couponId + '\'' +
                ", activityStartTime=" + activityStartTime +
                ", activityEndTime=" + activityEndTime +
                ", couponNum=" + couponNum +
                ", limit=" + limit +
                ", limitNum=" + limitNum +
                ", getType='" + getType + '\'' +
                ", validApi='" + validApi + '\'' +
                ", valid=" + valid +
                ", target='" + target + '\'' +
                ", areaOper='" + areaOper + '\'' +
                ", areaIds='" + areaIds + '\'' +
                ", tagOper='" + tagOper + '\'' +
                ", tagIds='" + tagIds + '\'' +
                ", regTimeOper='" + regTimeOper + '\'' +
                ", regStartTime=" + regStartTime +
                ", regEndTime=" + regEndTime +
                ", vips='" + vips + '\'' +
                ", userIds='" + userIds + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
