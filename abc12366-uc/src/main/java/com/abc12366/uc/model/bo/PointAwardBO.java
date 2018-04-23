package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-27
 * Time: 17:14
 */
public class PointAwardBO {
    @NotEmpty
    private String userId;
    @NotNull
    private Integer point;

    public PointAwardBO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "PointAwardBO{" +
                "userId='" + userId + '\'' +
                ", point=" + point +
                '}';
    }
}
