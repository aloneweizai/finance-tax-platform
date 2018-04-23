package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-23
 * Time: 20:00
 */
public class PointCodex {
    private String id;
    @NotEmpty
    private String upointruleCode;//积分规则编码
    @NotEmpty
    private String clientType;//客户端类型
    @NotEmpty
    private String uri;
    @NotNull
    private Integer upoint; //经验值
    @NotEmpty
    @Size(min = 1,max = 1)
    private String period;//周期 A:无周期,Y:年,M:月,D:日, H:小时
    @NotNull
    private Integer degree;//周期内次数,0无限次
    private String remark;//备注

    public PointCodex() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUpoint() {
        return upoint;
    }

    public void setUpoint(Integer upoint) {
        this.upoint = upoint;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getUpointruleCode() {
        return upointruleCode;
    }

    public void setUpointruleCode(String upointruleCode) {
        this.upointruleCode = upointruleCode;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
