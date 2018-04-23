package com.abc12366.uc.model.weixin.bo.redpack;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-28 11:26 PM
 * @since 1.0.0
 */
public class ActivityBO {

    // 活动编号（系统产生）
    private String id;
    // 活动名称
    private String name;
    // 活动描述
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ActivityBO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
