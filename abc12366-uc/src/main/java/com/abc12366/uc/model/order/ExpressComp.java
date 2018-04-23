package com.abc12366.uc.model.order;

import java.io.Serializable;


/**
 * 物流公司表
 **/
@SuppressWarnings("serial")
public class ExpressComp implements Serializable {

    /****/
    private String id;

    /**
     * 物流公司代号
     **/
    private String compCode;

    /**
     * 物流公司名称
     **/
    private String compName;

    /**
     * 物流公司URL
     **/
    private String compUrl;

    /**
     * 排序
     **/
    private Integer sort;

    /****/
    private java.util.Date createTime;

    /****/
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
