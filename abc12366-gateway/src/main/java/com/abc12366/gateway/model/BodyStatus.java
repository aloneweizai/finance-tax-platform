package com.abc12366.gateway.model;

/**
 * 处理成功或失败时的请求体
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-17 5:17 PM
 * @since 1.0.0
 */
public class BodyStatus {

    // 代码
    private String code;
    // 代码解释
    private String message;

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

    @Override
    public String toString() {
        return "BodyStatus{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
