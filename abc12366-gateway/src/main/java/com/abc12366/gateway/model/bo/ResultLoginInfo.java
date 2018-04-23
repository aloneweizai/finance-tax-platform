package com.abc12366.gateway.model.bo;

/**
 * Created by Administrator on 2017/7/31.
 */
public class ResultLoginInfo {
    private String code;
    private LoginInfoBO data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LoginInfoBO getData() {
        return data;
    }

    public void setData(LoginInfoBO data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultLoginInfo{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
