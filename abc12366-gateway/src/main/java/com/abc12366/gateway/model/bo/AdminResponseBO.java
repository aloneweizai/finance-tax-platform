package com.abc12366.gateway.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-03
 * Time: 15:48
 */
public class AdminResponseBO {
    private String code;
    private String message;
    private UserByTokenBO data;

    public AdminResponseBO() {
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

    public UserByTokenBO getData() {
        return data;
    }

    public void setData(UserByTokenBO data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AdminResponseBO{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
