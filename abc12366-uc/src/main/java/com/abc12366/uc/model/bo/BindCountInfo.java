package com.abc12366.uc.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-12
 * Time: 14:53
 */
public class BindCountInfo {
    /**
     * 纳税人识别号
     */
    private String nsrsbh;
    /**
     *纳税人名称
     */
    private String nsrmc;
    /**
     *税务机关代码
     */
    private String swjgdm;
    /**
     *税务机关名称
     */
    private String swjgmc;

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public String getSwjgdm() {
        return swjgdm;
    }

    public void setSwjgdm(String swjgdm) {
        this.swjgdm = swjgdm;
    }

    public String getSwjgmc() {
        return swjgmc;
    }

    public void setSwjgmc(String swjgmc) {
        this.swjgmc = swjgmc;
    }

    @Override
    public String toString() {
        return "BindCountInfo{" +
                "nsrsbh='" + nsrsbh + '\'' +
                ", nsrmc='" + nsrmc + '\'' +
                ", swjgdm='" + swjgdm + '\'' +
                ", swjgmc='" + swjgmc + '\'' +
                '}';
    }
}
