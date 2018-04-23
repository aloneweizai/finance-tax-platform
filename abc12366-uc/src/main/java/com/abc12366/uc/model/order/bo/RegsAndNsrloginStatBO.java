package com.abc12366.uc.model.order.bo;

/**
 * regsDay-今天用户注册数、regsMonth-本月注册数统计，dzsbLoginsDay-增加今天纳税人登录电子申报数、nsrLoginsMonth-本月纳税人登录数统计
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-10-25 3:30 PM
 * @since 1.0.0
 */
public class RegsAndNsrloginStatBO {
    /**
     * regsDay-今天用户注册数
     */
    private Integer regsDay;
    /**
     * regsMonth-本月注册数统计
     */
    private Integer regsMonth;
    /**
     * dzsbLoginsDay-增加今天纳税人登录电子申报数
     */
    private Integer dzsbLoginsDay;
    /**
     * nsrLoginsMonth-本月纳税人登录数统计
     */
    private Integer nsrLoginsMonth;

    public Integer getRegsDay() {
        return regsDay;
    }

    public void setRegsDay(Integer regsDay) {
        this.regsDay = regsDay;
    }

    public Integer getRegsMonth() {
        return regsMonth;
    }

    public void setRegsMonth(Integer regsMonth) {
        this.regsMonth = regsMonth;
    }

    public Integer getDzsbLoginsDay() {
        return dzsbLoginsDay;
    }

    public void setDzsbLoginsDay(Integer dzsbLoginsDay) {
        this.dzsbLoginsDay = dzsbLoginsDay;
    }

    public Integer getNsrLoginsMonth() {
        return nsrLoginsMonth;
    }

    public void setNsrLoginsMonth(Integer nsrLoginsMonth) {
        this.nsrLoginsMonth = nsrLoginsMonth;
    }

    @Override
    public String toString() {
        return "RegsAndNsrloginStatBO{" +
                "regsDay=" + regsDay +
                ", regsMonth=" + regsMonth +
                ", dzsbLoginsDay=" + dzsbLoginsDay +
                ", nsrLoginsMonth=" + nsrLoginsMonth +
                '}';
    }
}
