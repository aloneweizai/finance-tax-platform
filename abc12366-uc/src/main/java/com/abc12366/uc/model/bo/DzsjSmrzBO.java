package com.abc12366.uc.model.bo;

/**
 * 电子税局实名认证查询接口返回bo
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-02
 * Time: 14:58
 */
public class DzsjSmrzBO {
    //"msg":"处理成功","smrzbz":"N","code":"000"
    private String msg;
    private String smrzbz;
    private String code;

    public DzsjSmrzBO() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSmrzbz() {
        return smrzbz;
    }

    public void setSmrzbz(String smrzbz) {
        this.smrzbz = smrzbz;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "DzsjSmrzBO{" +
                "msg='" + msg + '\'' +
                ", smrzbz='" + smrzbz + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
