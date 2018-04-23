package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 会员特权新增对象
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-11-08 4:02 PM
 * @since 1.0.0
 */
public class VipPrivilegeBO {

    /**
     * PK
     */
    private String id;

    /**
     * 特权名称
     */
    @NotEmpty
    @Size(max = 100)
    private String name;

    /**
     * 特权代码
     */
    @NotEmpty
    @Length(max = 20)
    private String code;

    /**
     * 图标
     */
    @Size( max = 100)
    private String icon;

    /**
     * 描述
     */
    @Size(max = 1000)
    private String description;

    /**
     * 数据状态
     */
    @NotNull
    private Boolean status;

    /**
     * 排序
     */
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "VipPrivilegeInsertAndUpdateBO{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", sort=" + sort +
                '}';
    }
}
