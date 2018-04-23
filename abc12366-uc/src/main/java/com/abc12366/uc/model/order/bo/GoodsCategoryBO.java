package com.abc12366.uc.model.order.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 产品类别表
 **/
@SuppressWarnings("serial")
public class GoodsCategoryBO implements Serializable {

    private String id;
    @NotEmpty
    @Size(min = 2, max = 16)
    private String category;

    @NotEmpty
    @Size(min = 1, max = 64)
    private String parentId;
    private String parentName;

    @NotNull
    private Integer sort;
    private java.util.Date createTime;
    private java.util.Date lastUpdate;
    private List<GoodsCategoryBO> nodes = new ArrayList<GoodsCategoryBO>();

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

    public List<GoodsCategoryBO> getNodes() {
        return nodes;
    }

    public void setNodes(List<GoodsCategoryBO> nodes) {
        this.nodes = nodes;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
