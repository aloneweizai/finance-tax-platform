package com.abc12366.bangbang.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 敏感词配置
 *
 * @author lizhongwei
 * @create 2017-06-20
 * @since 1.0.0
 **/
@SuppressWarnings("serial")
public class SensitiveWords implements Serializable {

    /**
     * PK
     **/
    private String id;

    /**
     * 关键字
     **/
    private String keywords;

    /**
     * 是否有效
     **/
    private Boolean status;

    /**
     * 修改时间
     **/
    private java.util.Date updateTime;

    /**
     * 修改adminId
     **/
    private String updateAdmin;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Boolean getStatus() {
        return status;
    }

    public SensitiveWords setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public SensitiveWords setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getUpdateAdmin() {
        return updateAdmin;
    }

    public SensitiveWords setUpdateAdmin(String updateAdmin) {
        this.updateAdmin = updateAdmin;
        return this;
    }
}
