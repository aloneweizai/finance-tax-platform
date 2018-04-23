package com.abc12366.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-06-29 10:27 AM
 * @since 1.0.0
 */
public class VoteHistory {

    private String id;
    private String voteId;
    private String ip;
    private String userAgent;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    public VoteHistory() {
    }

    public VoteHistory(String id, String voteId, String ip, String userAgent, Timestamp createTime) {
        this.id = id;
        this.voteId = voteId;
        this.ip = ip;
        this.userAgent = userAgent;
        this.createTime = createTime;
    }

    private VoteHistory(Builder builder) {
        setId(builder.id);
        setVoteId(builder.voteId);
        setIp(builder.ip);
        setUserAgent(builder.userAgent);
        setCreateTime(builder.createTime);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "VoteHistory{" +
                "id='" + id + '\'' +
                ", voteId='" + voteId + '\'' +
                ", ip='" + ip + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public static final class Builder {
        private String id;
        private String voteId;
        private String ip;
        private String userAgent;
        private Timestamp createTime;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder voteId(String val) {
            voteId = val;
            return this;
        }

        public Builder ip(String val) {
            ip = val;
            return this;
        }

        public Builder userAgent(String val) {
            userAgent = val;
            return this;
        }

        public Builder createTime(Timestamp val) {
            createTime = val;
            return this;
        }

        public VoteHistory build() {
            return new VoteHistory(this);
        }
    }
}
