package com.abc12366.uc.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-25
 * Time: 10:29
 */
public class PointComputeBO {
    private String userId;
    private String uri;
    private String clientType;

    public PointComputeBO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "PointComputeBO{" +
                "userId='" + userId + '\'' +
                ", uri='" + uri + '\'' +
                ", clientType='" + clientType + '\'' +
                '}';
    }
}
