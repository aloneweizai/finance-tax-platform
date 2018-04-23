package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-12
 * Time: 14:35
 */
public class UpdatePwd {
    @NotEmpty(message = "纳税人识别号不能为空")
    private String nsrsbh;
    @NotEmpty(message = "旧密码不能为空")
    private String oldpwd;
    @NotEmpty(message = "新密码不能为空")
    private String newpwd;

    public UpdatePwd() {
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getOldpwd() {
        return oldpwd;
    }

    public void setOldpwd(String oldpwd) {
        this.oldpwd = oldpwd;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    @Override
    public String toString() {
        return "UpdatePwd{" +
                "nsrsbh='" + nsrsbh + '\'' +
                ", oldpwd='" + oldpwd + '\'' +
                ", newpwd='" + newpwd + '\'' +
                '}';
    }
}
