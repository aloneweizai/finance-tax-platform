package com.abc12366.uc.model.bo;

/**
 * 是否实名认证实体类
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-16
 * Time: 19:38
 */
public class IsRealNameBO {
    private Boolean isRealName;

    public IsRealNameBO() {
    }

    public Boolean getIsRealName() {
        return isRealName;
    }

    public void setIsRealName(Boolean isRealName) {
        this.isRealName = isRealName;
    }

    @Override
    public String toString() {
        return "IsRealNameBO{" +
                "isRealName=" + isRealName +
                '}';
    }
}
