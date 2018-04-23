package com.abc12366.gateway.model;

/**
 * 错误校验时的返回对象
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-02-23 4:53 PM
 * @since 1.0.0
 */
public class BodyValidStatus {

    // 错误代码
    private int code;
    // 错误代码解释
    private String message;
    // 错误字段
    private String field;

    public BodyValidStatus() {
    }

    public BodyValidStatus(int code, String message, String field) {
        this.code = code;
        this.message = message;
        this.field = field;
    }

    private BodyValidStatus(Builder builder) {
        setCode(builder.code);
        setMessage(builder.message);
        setField(builder.field);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "BodyValidStatus{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", field='" + field + '\'' +
                '}';
    }

    public static final class Builder {
        private int code;
        private String message;
        private String field;

        public Builder() {
        }

        public Builder code(int val) {
            code = val;
            return this;
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public Builder field(String val) {
            field = val;
            return this;
        }

        public BodyValidStatus build() {
            return new BodyValidStatus(this);
        }
    }
}
