package com.abc12366.gateway.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-07-10
 * Time: 14:34
 */
public class UserResponseBO {
    private String code;
    private String message;
    private UCUserBO data;

    public UserResponseBO() {
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

    public UCUserBO getData() {
        return data;
    }

    public void setData(UCUserBO data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserResponseBO{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
