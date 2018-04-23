package com.abc12366.uc.model.weixin;

public class BaseWxRespon implements java.io.Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer errcode = 0;
    private String errmsg = "ok";


    public BaseWxRespon() {

    }

    public BaseWxRespon(String code, String message) {
        this.errmsg = code;
        this.errmsg = message;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }


}
