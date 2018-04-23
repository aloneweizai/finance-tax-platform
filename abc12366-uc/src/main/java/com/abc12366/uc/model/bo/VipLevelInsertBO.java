package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-22
 * Time: 16:51
 */
public class VipLevelInsertBO {
    @NotEmpty
    @Size(max = 10)
    private String level;
    private Double factor;
    @NotNull
    private Boolean status;
    @Size(max = 10)
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

	public VipLevelInsertBO() {
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
