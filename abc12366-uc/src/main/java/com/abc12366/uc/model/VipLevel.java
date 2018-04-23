package com.abc12366.uc.model;

import java.util.Date;

/**
 * @author liuguiyao<435720953@qq.com.com>
 * @create 2017-05-19 10:18 PM
 * @since 2.0.0
 */
public class VipLevel {
    private String id;
    private String level;
    private Double factor;
    private Boolean status;
    private Date lastUpdate;
    private Date createTime;
    private String levelCode;
    private Double costPrice;
    private Double marketPrice;
    private Double salePrice;
    private Double sendPoints;
    private String imgUrl;
    /**
     * 积分价格
     */
    private Double pointsPrice;
    
    
    public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getSendPoints() {
		return sendPoints;
	}

	public void setSendPoints(Double sendPoints) {
		this.sendPoints = sendPoints;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public VipLevel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public Double getPointsPrice() {
        return pointsPrice;
    }

    public void setPointsPrice(Double pointsPrice) {
        this.pointsPrice = pointsPrice;
    }
}
