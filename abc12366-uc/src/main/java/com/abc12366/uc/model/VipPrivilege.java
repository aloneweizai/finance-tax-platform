package com.abc12366.uc.model;

import java.util.Date;

/**
 * 会员特权对象
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-11-08 4:02 PM
 * @since 1.0.0
 */
public class VipPrivilege {
    private String id;

    /**
     * 特权名称
     */
    private String name;

    /**
     * 特权代码
     */
    private String code;

    /**
     * 图标
     */
    private String icon;

    /**
     * 描述
     */
    private String description;

    /**
     * 数据状态
     */
    private Boolean status;

    /**
     * 更新时间
     */
    private Date lastUpdate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 排序
     */
    private Integer sort;

    public VipPrivilege() {
    }

    private VipPrivilege(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setCode(builder.code);
        setIcon(builder.icon);
        setDescription(builder.description);
        setStatus(builder.status);
        setLastUpdate(builder.lastUpdate);
        setCreateTime(builder.createTime);
        setSort(builder.sort);
    }

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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "VipPrivilege{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", lastUpdate=" + lastUpdate +
                ", createTime=" + createTime +
                ", sort=" + sort +
                '}';
    }

    public static final class Builder {
        private String id;
        private String name;
        private String code;
        private String icon;
        private String description;
        private Boolean status;
        private Date lastUpdate;
        private Date createTime;
        private Integer sort;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder code(String val) {
            code = val;
            return this;
        }

        public Builder icon(String val) {
            icon = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder status(Boolean val) {
            status = val;
            return this;
        }

        public Builder lastUpdate(Date val) {
            lastUpdate = val;
            return this;
        }

        public Builder createTime(Date val) {
            createTime = val;
            return this;
        }

        public Builder sort(Integer val) {
            sort = val;
            return this;
        }

        public VipPrivilege build() {
            return new VipPrivilege(this);
        }
    }
}

