package com.abc12366.uc.model.bo;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-06-16
 * Time: 15:38
 */
public class NsrBindQueryParamBO {
    private String userId;
    private String nsrsbh;
    private Boolean status;

    public NsrBindQueryParamBO() {
    }

    private NsrBindQueryParamBO(Builder builder) {
        setUserId(builder.userId);
        setNsrsbh(builder.nsrsbh);
        setStatus(builder.status);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    @Override
    public String toString() {
        return "NsrBindQueryParamBO{" +
                "userId='" + userId + '\'' +
                ", nsrsbh='" + nsrsbh + '\'' +
                ", status=" + status +
                '}';
    }

    public static final class Builder {
        private String userId;
        private String nsrsbh;
        private Boolean status;

        public Builder() {
        }

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public Builder nsrsbh(String val) {
            nsrsbh = val;
            return this;
        }

        public Builder status(Boolean val) {
            status = val;
            return this;
        }

        public NsrBindQueryParamBO build() {
            return new NsrBindQueryParamBO(this);
        }
    }
}
