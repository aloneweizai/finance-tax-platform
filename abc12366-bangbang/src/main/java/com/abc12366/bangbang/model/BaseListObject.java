package com.abc12366.bangbang.model;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-08
 * Time: 10:38
 */
public class BaseListObject {
    private String code;
    private String message;
    private String total;

    public BaseListObject() {
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
