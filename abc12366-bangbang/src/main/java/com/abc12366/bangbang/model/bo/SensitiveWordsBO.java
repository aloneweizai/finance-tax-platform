package com.abc12366.bangbang.model.bo;

import java.io.Serializable;


/**
 * 敏感词配置
 **/
@SuppressWarnings("serial")
public class SensitiveWordsBO implements Serializable {

    /**
     * PK
     **/
    private String id;

    /**
     * 关键字，多个关键字逗号分隔
     **/
    private String keywords;

    /**
     * 排序
     **/
    private Integer sort;

    /**
     * 匹配类型：1.全匹配 2.模糊匹配
     **/
    private String type;

    /****/
    private java.util.Date createTime;

    /****/
    private java.util.Date lastUpdate;

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

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
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

}
