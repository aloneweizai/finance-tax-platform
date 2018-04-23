package com.abc12366.uc.model.order.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 物流公司表
 **/
@SuppressWarnings("serial")
public class ExpressCompBO implements Serializable {

    private String id;
    @NotEmpty
    @Size(min = 2, max = 10)
    private String compCode;
    @NotEmpty
    @Size(min = 2, max = 50)
    private String compName;
    @Size(min = 0, max = 100)
    private String compUrl;
    private Integer sort;
    private java.util.Date createTime;
    private java.util.Date lastUpdate;

    /**模版下载地址**/
    private String templateUrl;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompCode() {
        return this.compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getCompName() {
        return this.compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCompUrl() {
        return this.compUrl;
    }

    public void setCompUrl(String compUrl) {
        this.compUrl = compUrl;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }
}
