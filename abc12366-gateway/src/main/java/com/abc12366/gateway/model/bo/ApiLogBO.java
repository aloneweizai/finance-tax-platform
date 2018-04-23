package com.abc12366.gateway.model.bo;

import java.io.Serializable;


/**
 * 接口调用日志表
 **/
@SuppressWarnings("serial")
public class ApiLogBO implements Serializable {

    /**
     * ID
     **/
    private String id;

    /**
     * 访问接口地址
     **/
    private String uri;

    /**
     * 用户代理
     **/
    private String userAgent;

    /**
     * 接入userId
     **/
    private String userId;

    /**
     * 接入AppId
     **/
    private String appId;

    /**
     * 接入IP地址
     **/
    private String ip;

    /**
     * 访问时间
     **/
    private long inTime;

    /**
     * 响应时间
     **/
    private long outTime;

    /**
     * 结果代码
     **/
    private String status;

    /**
     * 附言
     **/
    private String version;

    /**
     * 返回代码
     **/
    private String code;

    /**
     * 返回消息
     **/
    private String message;

    /**
     * 接口方法
     **/
    private String method;

    /**app昵称**/
    private String nickname;

    /**APP访问次数**/
    private Integer appAccessCount;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getInTime() {
        return this.inTime;
    }

    public void setInTime(long inTime) {
        this.inTime = inTime;
    }

    public long getOutTime() {
        return this.outTime;
    }

    public void setOutTime(long outTime) {
        this.outTime = outTime;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAppAccessCount() {
        return appAccessCount;
    }

    public void setAppAccessCount(Integer appAccessCount) {
        this.appAccessCount = appAccessCount;
    }

    @Override
    public String toString() {
        return "ApiLogBO{" +
                "id='" + id + '\'' +
                ", uri='" + uri + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", userId='" + userId + '\'' +
                ", appId='" + appId + '\'' +
                ", ip='" + ip + '\'' +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", status='" + status + '\'' +
                ", version='" + version + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", method='" + method + '\'' +
                ", nickname='" + nickname + '\'' +
                ", appAccessCount=" + appAccessCount +
                '}';
    }
}
