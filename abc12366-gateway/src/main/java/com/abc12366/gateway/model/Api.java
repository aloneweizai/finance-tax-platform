package com.abc12366.gateway.model;

import java.io.Serializable;


/**
 * 服务接口表
 **/
@SuppressWarnings("serial")
public class Api implements Serializable {

    /**
     * ID
     **/
    private String id;

    /**
     * 接口名称
     **/
    private String name;

    /**
     * 接口地址
     **/
    private String uri;

    /**
     * 接口方法
     **/
    private String method;

    /**
     * 版本
     **/
    private String version;

    /**
     * 接口所属系统，字典ID
     **/
    private String dictId;

    /**
     * 是否需要验证用户身份: 0不需要，1需要
     **/
    private Boolean authentication;

    /**
     * 接口状态：0停用，1启用
     **/
    private Boolean status;

    /**
     * 创建时间
     **/
    private java.util.Date createTime;

    /**
     * 最后修改时间
     **/
    private java.util.Date lastUpdate;

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

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getAuthentication() {
        return this.authentication;
    }

    public void setAuthentication(Boolean authentication) {
        this.authentication = authentication;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    @Override
    public String toString() {
        return "Api{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", uri='" + uri + '\'' +
                ", method='" + method + '\'' +
                ", version='" + version + '\'' +
                ", dictId='" + dictId + '\'' +
                ", authentication=" + authentication +
                ", status=" + status +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
