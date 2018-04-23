package com.abc12366.uc.wsbssoa.response;

import java.util.Date;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-28
 * Time: 16:48
 */
public class HngsAppLoginResponse {
    private String msg;
    private int code;
    private String accessToken;
    private Date expiresTime;

    public HngsAppLoginResponse() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(Date expiresTime) {
        this.expiresTime = expiresTime;
    }
}
