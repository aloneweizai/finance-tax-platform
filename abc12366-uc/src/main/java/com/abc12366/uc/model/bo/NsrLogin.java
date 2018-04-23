package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-11
 * Time: 16:20
 */
public class NsrLogin {
    @NotEmpty
    private String nsrsbhOrShxydm;
    @NotEmpty
    private String fwmm;

    public NsrLogin() {
    }

    public String getNsrsbhOrShxydm() {
        return nsrsbhOrShxydm;
    }

    public void setNsrsbhOrShxydm(String nsrsbhOrShxydm) {
        this.nsrsbhOrShxydm = nsrsbhOrShxydm;
    }

    public String getFwmm() {
        return fwmm;
    }

    public void setFwmm(String fwmm) {
        this.fwmm = fwmm;
    }

    @Override
    public String toString() {
        return "NsrLogin{" +
                "nsrsbhOrShxydm='" + nsrsbhOrShxydm + '\'' +
                ", fwmm='" + fwmm + '\'' +
                '}';
    }
}
