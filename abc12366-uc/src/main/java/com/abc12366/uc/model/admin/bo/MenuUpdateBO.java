package com.abc12366.uc.model.admin.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0.0
 */
public class MenuUpdateBO implements Serializable {

    private String menuId;
    private String menuName;
    private String menuUrl;
    private String parentId;
    private String parentName;
    private String perms;
    private String type;
    private String icon;
    private Integer sort;
    private Boolean status;
    private String remark;

    private List<MenuUpdateBO> nodes = new ArrayList<MenuUpdateBO>();

    public MenuUpdateBO() {
    }

    public MenuUpdateBO(String menuId, String menuName, String menuUrl, String parentId, String perms, String type,
                        String icon, Integer sort, Boolean status, String remark) {
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

    public List<MenuUpdateBO> getNodes() {
        return nodes;
    }

    public void setNodes(List<MenuUpdateBO> nodes) {
        this.nodes = nodes;
    }
}
