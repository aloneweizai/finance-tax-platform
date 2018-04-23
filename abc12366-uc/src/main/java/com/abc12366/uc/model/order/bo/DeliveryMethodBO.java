package com.abc12366.uc.model.order.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 配送方式
 **/
@SuppressWarnings("serial")
public class DeliveryMethodBO implements Serializable {

    private String id;
    @NotEmpty
    @Size(min = 2, max = 10)
    private String name;
    private String type;
    private Integer sort;
    private Boolean status;
    @Size(min = 0, max = 200)
    private String description;
    private java.util.Date createTime;
    private java.util.Date lastUpdate;
    private Double firstWeight;
    private Double firstWeightFee;
    private Double addedWeight;
    private Double addedWeightFee;
    private Integer isInsured;
    private Double rate;
    private Double minInsuredFee;
    private String areaFeeType;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(java.util.Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Double getFirstWeight() {
        return this.firstWeight;
    }

    public void setFirstWeight(Double firstWeight) {
        this.firstWeight = firstWeight;
    }

    public Double getFirstWeightFee() {
        return this.firstWeightFee;
    }

    public void setFirstWeightFee(Double firstWeightFee) {
        this.firstWeightFee = firstWeightFee;
    }

    public Double getAddedWeight() {
        return this.addedWeight;
    }

    public void setAddedWeight(Double addedWeight) {
        this.addedWeight = addedWeight;
    }

    public Double getAddedWeightFee() {
        return this.addedWeightFee;
    }

    public void setAddedWeightFee(Double addedWeightFee) {
        this.addedWeightFee = addedWeightFee;
    }

    public Integer getIsInsured() {
        return this.isInsured;
    }

    public void setIsInsured(Integer isInsured) {
        this.isInsured = isInsured;
    }

    public Double getRate() {
        return this.rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getMinInsuredFee() {
        return this.minInsuredFee;
    }

    public void setMinInsuredFee(Double minInsuredFee) {
        this.minInsuredFee = minInsuredFee;
    }

    public String getAreaFeeType() {
        return this.areaFeeType;
    }

    public void setAreaFeeType(String areaFeeType) {
        this.areaFeeType = areaFeeType;
    }

}
