package com.abc12366.uc.model.abc4000;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-10
 * Time: 10:34
 */
public class ABC4000CallbackBO {
    //用户ID
    @NotEmpty(message = "用户ID不能为空")
    private String userid;
    //纳税人识别号
    @Size(max = 32, message = "纳税人识别号长度不能超过32")
    private String nsrsbh;
    //社会信用代码
    @Size(max = 32, message = "社会信用代码长度不能超过32")
    private String shxydm;
    //登记序号
    @NotEmpty(message = "登记序号不能为空")
    @Size(max = 64, message = "登记序号长度不能超过64")
    private String djxh;
    //税务机关代码
    @Size(max = 32, message = "税务机关代码长度不能超过32")
    private String swjgdm;
    //税务机关名称
    @Size(max = 200, message = "税务机关名称长度不能超过200")
    private String swjgmc;
    //软件到期日
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp rjdqr;
    //软件延期到期日
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp yqdqr;
    //法人名称
    @Size(max = 20, message = "法人名称长度不能超过20")
    private String frmc;
    //法人证件号
    @Size(max = 64, message = "法人证件号长度不能超过64")
    private String frzjh;
    //纳税人类型 0未核定，1增值税小规模 2：增值税一般 :3：纯所得税
    @Size(max = 30, message = "纳税人类型长度不能超过30")
    private String nsrlx;
    //是否个体建账户 Y/N
    @Size(max = 30, message = "是否个体建账户不能超过30")
    private String sfgtjzh;

    public ABC4000CallbackBO() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
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

    public String getNsrlx() {
        return nsrlx;
    }

    public void setNsrlx(String nsrlx) {
        this.nsrlx = nsrlx;
    }

    public String getSfgtjzh() {
        return sfgtjzh;
    }

    public void setSfgtjzh(String sfgtjzh) {
        this.sfgtjzh = sfgtjzh;
    }

    @Override
    public String toString() {
        return "ABC4000CallbackBO{" +
                "userid='" + userid + '\'' +
                ", nsrsbh='" + nsrsbh + '\'' +
                ", shxydm='" + shxydm + '\'' +
                ", djxh='" + djxh + '\'' +
                ", swjgdm='" + swjgdm + '\'' +
                ", swjgmc='" + swjgmc + '\'' +
                ", rjdqr=" + rjdqr +
                ", yqdqr=" + yqdqr +
                ", frmc='" + frmc + '\'' +
                ", frzjh='" + frzjh + '\'' +
                ", nsrlx='" + nsrlx + '\'' +
                ", sfgtjzh='" + sfgtjzh + '\'' +
                '}';
    }
}
