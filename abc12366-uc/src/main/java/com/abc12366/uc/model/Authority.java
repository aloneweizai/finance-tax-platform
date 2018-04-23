package com.abc12366.uc.model;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-03-27 5:57 PM
 * @since 1.0.0
 */
public class Authority {

    private String userId;

    private String authority;

    public Authority() {
    }

    public Authority(String userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }

    private Authority(Builder builder) {
        setUserId(builder.userId);
        setAuthority(builder.authority);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "userId=" + userId +
                ", authority='" + authority + '\'' +
                '}';
    }

    public static final class Builder {
        private String userId;
        private String authority;

        public Builder() {
        }

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public Builder authority(String val) {
            authority = val;
            return this;
        }

        public Authority build() {
            return new Authority(this);
        }
    }
}
