package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-30
 * Time: 17:08
 */
public class PointsRuleInsertBO {
    @NotEmpty
    @Size(max = 32)
    private String name;
    @NotEmpty
    @Size(max = 10)
    private String code;
    @NotNull
    private Integer points;
    private String description;
    @Size(max = 1)
    private String type;
    @NotNull
    private Boolean status;
    @NotEmpty
    private String period;
    @NotNull
    private Integer degree;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }
}
