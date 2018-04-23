package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 新增经验值规则入参实体类
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-23
 * Time: 11:06
 */
public class ExperienceRuleInsertBO {
    //规则名称
    @NotEmpty(message = "规则名称不能为空")
    @Size(max = 32)
    private String name;

    //规则编码
    @NotEmpty
    @Size(max = 10)
    private String code;

    //经验值
    @NotNull(message = "经验数值不能为空")
    private Integer exp;

    //描述
    @Size(max = 1000)
    private String description;

    //经验值类型
    @Size(max = 1)
    private String type;

    //规则状态
    @NotNull
    private Boolean status;

    //经验值规则周期限制:D:日，M：月，Y：年，A：无限制
    @NotEmpty(message = "规则周期不能为空")
    private String period;

    //经验值规则频率/次数
    @NotNull
    private Integer degree;

    public ExperienceRuleInsertBO() {
    }

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

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
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

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "ExperienceRuleInsertBO{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", exp=" + exp +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", period='" + period + '\'' +
                ", degree=" + degree +
                '}';
    }
}