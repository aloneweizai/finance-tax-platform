package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 新增或修改经验值等级接口入参实体类
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-23
 * Time: 10:25
 */
public class ExperienceLevelInsertAndUpdateBO {
    //等级名称
    @NotEmpty
    @Size(max = 10)
    private String name;

    //经验值范围下界
    @NotNull
    private Integer minValue;

    //经验值范围上界
    @NotNull
    private Integer maxValue;

    //每日封顶
    private Integer topPerDay;

    //用户等级规则状态：1：有效，0：失效
    private Boolean status;

    //经验值等级中文名称（勋章）
    @Size(max = 50)
    private String medal;

    //勋章图标
    @Size(max = 50)
    private String medalIcon;

    public ExperienceLevelInsertAndUpdateBO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getTopPerDay() {
        return topPerDay;
    }

    public void setTopPerDay(Integer topPerDay) {
        this.topPerDay = topPerDay;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMedal() {
        return medal;
    }

    public void setMedal(String medal) {
        this.medal = medal;
    }

    public String getMedalIcon() {
        return medalIcon;
    }

    public void setMedalIcon(String medalIcon) {
        this.medalIcon = medalIcon;
    }

    @Override
    public String toString() {
        return "ExperienceLevelInsertAndUpdateBO{" +
                "name='" + name + '\'' +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", topPerDay=" + topPerDay +
                ", status=" + status +
                ", medal='" + medal + '\'' +
                ", medalIcon='" + medalIcon + '\'' +
                '}';
    }
}
