package com.abc12366.uc.model.abc4000;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-10
 * Time: 20:51
 */
public class NSRXXBO {
    @NotEmpty
    @Size(max = 32)
    private String y_nsrsbh;
    @NotEmpty
    @Size(max = 32)
    private String shxydm;
    @NotEmpty
    @Size(max = 64)
    private String djxh;
    @NotEmpty
    @Size(max = 32)
    private String swjgdm;
    @NotEmpty
    @Size(max = 200)
    private String swjgmc;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp rjdqr;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp yqdqr;
    @NotEmpty
    @Size(max = 20)
    private String frmc;
    @NotEmpty
    @Size(max = 64)
    private String frzjh;

    public NSRXXBO() {
    }

    public String getY_nsrsbh() {
        return y_nsrsbh;
    }

    public void setY_nsrsbh(String y_nsrsbh) {
        this.y_nsrsbh = y_nsrsbh;
    }

    public String getShxydm() {
        return shxydm;
    }

    public void setShxydm(String shxydm) {
        this.shxydm = shxydm;
    }

    public String getDjxh() {
        return djxh;
    }

    public void setDjxh(String djxh) {
        this.djxh = djxh;
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

    public Timestamp getRjdqr() {
        return rjdqr;
    }

    public void setRjdqr(Timestamp rjdqr) {
        this.rjdqr = rjdqr;
    }

    public Timestamp getYqdqr() {
        return yqdqr;
    }

    public void setYqdqr(Timestamp yqdqr) {
        this.yqdqr = yqdqr;
    }

    public String getFrmc() {
        return frmc;
    }

    public void setFrmc(String frmc) {
        this.frmc = frmc;
    }

    public String getFrzjh() {
        return frzjh;
    }

    public void setFrzjh(String frzjh) {
        this.frzjh = frzjh;
    }
}
