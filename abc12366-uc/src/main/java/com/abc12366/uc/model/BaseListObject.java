package com.abc12366.uc.model;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-08
 * Time: 10:38
 */
public class BaseListObject {
    private String code;
    private String message;
    private int total;
    private String time;

    public BaseListObject() {
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "BaseListObject{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", total=" + total +
                ", time='" + time + '\'' +
                '}';
    }
}
