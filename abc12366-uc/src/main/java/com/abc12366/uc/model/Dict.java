package com.abc12366.uc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-24 3:10 PM
 * @since 1.0.0
 */
public class Dict implements Serializable {

    private String id;
    private String dictId;
    private String dictName;
    private String fieldKey;
    private String fieldValue;
    private Boolean status;
    private Date createTime;
    private String createUser;
    private Date lastUpdate;
    private String lastUser;
    private Integer sort;

    public Dict() {
    }

    public Dict(String id, String dictId, String dictName, String fieldKey, String fieldValue, Boolean status, Date
            createTime, String createUser, Date lastUpdate, String lastUser, Integer sort) {
        this.id = id;
        this.dictId = dictId;
        this.dictName = dictName;
        this.fieldKey = fieldKey;
        this.fieldValue = fieldValue;
        this.status = status;
        this.createTime = createTime;
        this.createUser = createUser;
        this.lastUpdate = lastUpdate;
        this.lastUser = lastUser;
        this.sort = sort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUser() {
        return lastUser;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    @Override
    public String toString() {
        return "Dict{" +
                "id='" + id + '\'' +
                ", dictId='" + dictId + '\'' +
                ", dictName='" + dictName + '\'' +
                ", fieldKey='" + fieldKey + '\'' +
                ", fieldValue='" + fieldValue + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", lastUser='" + lastUser + '\'' +
                '}';
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
