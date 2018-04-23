package com.abc12366.uc.model.weixin.bo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 标识符集合
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-10-19 4:06 PM
 * @since 1.0.0
 */
public class Id {

    @NotEmpty
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id{" +
                "id='" + id + '\'' +
                '}';
    }
}
