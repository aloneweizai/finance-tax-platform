package com.abc12366.uc.model.order;

import java.io.Serializable;


/**
 * 产品类别表
 **/
@SuppressWarnings("serial")
public class GoodsCategory implements Serializable {

    /****/
    private String id;

    /**
     * 类别名称
     **/
    private String category;

    /**
     * 父ID
     **/
    private String parentId;

    /**
     * 排序
     **/
    private Integer sort;

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

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

}
