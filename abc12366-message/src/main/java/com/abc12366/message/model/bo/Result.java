package com.abc12366.message.model.bo;

/**
 * 调用接口返回结果
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-07-13 4:10 PM
 * @since 1.0.0
 */
public class Result {
    //返回码
    private String code;
    //返回内容
    private String message;
    //返回信息
    private String msg;

    public Result() {
    }

    private Result(Builder builder) {
        setCode(builder.code);
        setMessage(builder.message);
        setMsg(builder.msg);
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static final class Builder {
        private String code;
        private String message;
        private String msg;

        public Builder() {
        }

        public Builder code(String val) {
            code = val;
            return this;
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public Builder msg(String val) {
            msg = val;
            return this;
        }

        public Result build() {
            return new Result(this);
        }
    }
}
