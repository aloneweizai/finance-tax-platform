package com.abc12366.uc.model.bo;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-02
 * Time: 10:52
 */
public class VerifyCodeResponse {
    private String code;

    public VerifyCodeResponse() {
    }

    public VerifyCodeResponse(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "VerifyCodeResponse{" +
                "code='" + code + '\'' +
                '}';
    }
}
