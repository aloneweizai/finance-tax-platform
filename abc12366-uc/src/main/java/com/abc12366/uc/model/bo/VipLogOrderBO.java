package com.abc12366.uc.model.bo;

import java.util.Date;

/**
 * Admin: lizhongwei
 * Date: 2018-01-12
 * Time: 15:38
 */
public class VipLogOrderBO {
    private String id;
    private String userId;
    private String source;
    private String levelId;
    /**
     * 会员等级描述
     */
    private String level;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 会员到期时间
     */
    private Date vipExpireDate;
    /**
     * 订单状态
     */
    private String orderStatus;

    public VipLogOrderBO() {
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getVipExpireDate() {
        return vipExpireDate;
    }

    public void setVipExpireDate(Date vipExpireDate) {
        this.vipExpireDate = vipExpireDate;
    }

    @Override
    public String toString() {
        return "VipLogBO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", source='" + source + '\'' +
                ", levelId='" + levelId + '\'' +
                ", createTime=" + createTime +
                ", vipExpireDate=" + vipExpireDate +
                '}';
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
