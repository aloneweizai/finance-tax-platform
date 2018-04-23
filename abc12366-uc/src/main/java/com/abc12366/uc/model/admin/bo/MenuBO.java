package com.abc12366.uc.model.admin.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuguiyao
 * @create 2017-04-27 10:08 AM
 * @since 1.0.0
 */
public class MenuBO implements Serializable {

    private String menuId;
    @NotEmpty
    @Size(min = 2, max = 32)
    private String menuName;
    //@NotEmpty(message = "menuUrl不能为空")
    @Size(min = 0, max = 128)
    private String menuUrl;
    private String parentId;
    private String parentName;
    @Size(min = 0, max = 500)
    private String perms;
    private String type;
    private String icon;
    private Integer sort;
    @NotNull
    private Boolean status;
    @Size(min = 0, max = 100)
    private String remark;

    private List<MenuBO> nodes = new ArrayList<MenuBO>();

    public MenuBO() {
    }

    public MenuBO(String menuId, String menuName, String menuUrl, String parentId, String perms, String type, String
            icon, Integer sort, Boolean status, String remark) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.parentId = parentId;
        this.perms = perms;
        this.type = type;
        this.icon = icon;
        this.sort = sort;
        this.status = status;
        this.remark = remark;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MenuBO{" +
                "menuId='" + menuId + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", parentId='" + parentId + '\'' +
                ", perms='" + perms + '\'' +
                ", type='" + type + '\'' +
                ", icon='" + icon + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<MenuBO> getNodes() {
        return nodes;
    }

    public void setNodes(List<MenuBO> nodes) {
        this.nodes = nodes;
    }
}
