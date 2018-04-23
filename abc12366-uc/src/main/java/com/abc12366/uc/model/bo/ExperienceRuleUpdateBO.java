package com.abc12366.uc.model.bo;

import javax.validation.constraints.Size;

/**
 * 修改经验值规则入参实体类
 * Admin: liuguiyao<435720953@qq.com.com>
 * Date: 2017-05-22
 * Time: 11:50
 */
public class ExperienceRuleUpdateBO {
    //规则名称
    @Size(max = 32,message = "规则名称参数name过长")
    private String name;

    //规则编码
    @Size(max = 10,message = "规则编码参数code过长")
    private String code;

    //经验值
    private int exp;

    //描述
    @Size(max = 1000)
    private String description;

    //经验值类型
    @Size(max = 1,message = "经验值类型参数type过长")
    private String type;

    //规则状态
    private Boolean status;

    //经验值规则周期限制:D:日，M：月，Y：年，A：无限制
    @Size(max = 5,message = "周期参数period过长")
    private String period;

    //经验值规则频率/次数
    private Integer degree;

    public ExperienceRuleUpdateBO() {
    }

    public ExperienceRuleUpdateBO(String name, String code, int exp, String description, String type, Boolean status) {
        this.name = name;
        this.code = code;
        this.exp = exp;
        this.description = description;
        this.type = type;
        this.status = status;
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

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
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

    @Override
    public String toString() {
        return "ExperienceRuleUpdateBO{" +
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
