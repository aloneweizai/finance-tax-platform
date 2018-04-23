package com.abc12366.uc.model.admin;

import java.io.Serializable;

/**
 * @author liuguiyao
 * @create 2017-04-27 10:08 AM
 * @since 1.0.0
 */
public class Menu implements Serializable {

    /**功能ID**/
    private String menuId;

    /**功能名称**/
    private String menuName;

    /**功能地址**/
    private String menuUrl;

    /**父菜单ID**/
    private String parentId;

    /**授权（多个用逗号分割，如：user:list,user:create)**/
    private String perms;

    /**类型：1目录，2菜单，3按钮**/
    private String type;

    /**图标样式**/
    private String icon;

    /**排序**/
    private Integer sort;

    /**状态：0无效，1有效**/
    private Boolean status;

    /**备注**/
    private String remark;



    public void setMenuId(String menuId){
        this.menuId = menuId;
    }

    public String getMenuId(){
        return this.menuId;
    }

    public void setMenuName(String menuName){
        this.menuName = menuName;
    }

    public String getMenuName(){
        return this.menuName;
    }

    public void setMenuUrl(String menuUrl){
        this.menuUrl = menuUrl;
    }

    public String getMenuUrl(){
        return this.menuUrl;
    }

    public void setParentId(String parentId){
        this.parentId = parentId;
    }

    public String getParentId(){
        return this.parentId;
    }

    public void setPerms(String perms){
        this.perms = perms;
    }

    public String getPerms(){
        return this.perms;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public void setIcon(String icon){
        this.icon = icon;
    }

    public String getIcon(){
        return this.icon;
    }

    public void setSort(Integer sort){
        this.sort = sort;
    }

    public Integer getSort(){
        return this.sort;
    }

    public void setStatus(Boolean status){
        this.status = status;
    }

    public Boolean getStatus(){
        return this.status;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return this.remark;
    }

    @Override
    public String toString() {
        return "Menu{" +
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
}
