package com.abc12366.uc.model.bo;

import javax.validation.constraints.Size;

/**
 * 修改积分规则入参实体类
 * @author liuguiyao<435720953@qq.com.com>
 * @create 2017-05-16 10:18 PM
 * @since 2.0.0
 */
public class PointsRuleUpdateBO {
    //规则名称
    @Size(max = 32)
    private String name;

    //规则编码
    @Size(max = 10)
    private String code;

    //规则对应的积分值
    private int points;

    //描述
    private String description;

    //规则类型
    @Size(max = 1)
    private String type;

    //规则状态
    private Boolean status;

    //周期限制:D:日，M：月，Y：年，A：无限制
    @Size(max = 5)
    private String period;

    //次数
    private Integer degree;

    public PointsRuleUpdateBO() {
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
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

    @Override
    public String toString() {
        return "PointsRuleUpdateBO{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", points=" + points +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", period='" + period + '\'' +
                ", degree=" + degree +
                '}';
    }
}
