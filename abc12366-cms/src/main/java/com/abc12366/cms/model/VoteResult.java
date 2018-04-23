package com.abc12366.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Timestamp;

/**
 * 投票结果表
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-06-08 5:41 PM
 * @since 1.0.0
 */
public class VoteResult {

    private String id;

    private String voteId;

    @NotEmpty
    private String subjectId;

    @NotEmpty
    private String itemId;

    private String userId;

    private String openId;

    private String ip;

    private String userAgent;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    public VoteResult() {
    }

    public VoteResult(String id, String voteId, String subjectId, String itemId, String userId, String openId,
                      String ip, String userAgent, Timestamp createTime) {
        this.id = id;
        this.voteId = voteId;
        this.subjectId = subjectId;
        this.itemId = itemId;
        this.userId = userId;
        this.openId = openId;
        this.ip = ip;
        this.userAgent = userAgent;
        this.createTime = createTime;
    }

    private VoteResult(Builder builder) {
        setId(builder.id);
        setVoteId(builder.voteId);
        setSubjectId(builder.subjectId);
        setItemId(builder.itemId);
        setUserId(builder.userId);
        setOpenId(builder.openId);
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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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
        return "VoteResult{" +
                "id='" + id + '\'' +
                ", voteId='" + voteId + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", userId='" + userId + '\'' +
                ", openId='" + openId + '\'' +
                ", ip='" + ip + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public static final class Builder {
        private String id;
        private String voteId;
        private String subjectId;
        private String itemId;
        private String userId;
        private String openId;
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

        public Builder subjectId(String val) {
            subjectId = val;
            return this;
        }

        public Builder itemId(String val) {
            itemId = val;
            return this;
        }

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public Builder openId(String val) {
            openId = val;
            return this;
        }

        public Builder userAgent(String val) {
            userAgent = val;
            return this;
        }

        public Builder ip(String val) {
            ip = val;
            return this;
        }

        public Builder createTime(Timestamp val) {
            createTime = val;
            return this;
        }

        public VoteResult build() {
            return new VoteResult(this);
        }
    }
}
