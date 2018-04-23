package com.abc12366.message.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * 批量用户消息对象
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-07-27 11:21 AM
 * @since 1.0.0
 */
public class UserBatchMessage {

    private String id;

    @NotEmpty
    @Length(min = 1, max = 64)
    private String fromUserId;

    @NotNull
    private List<String> toUserIds;

    @NotEmpty
    @Length(min = 1, max = 500)
    private String content;

    @Length(min = 1, max = 1)
    private String status;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp lastUpdate;

    @Length(min = 1, max = 1)
    private String type;

    public UserBatchMessage() {
    }

    private UserBatchMessage(Builder builder) {
        setId(builder.id);
        setFromUserId(builder.fromUserId);
        setToUserIds(builder.toUserIds);
        setContent(builder.content);
        setStatus(builder.status);
        setCreateTime(builder.createTime);
        setLastUpdate(builder.lastUpdate);
        setType(builder.type);
    }

    @Override
    public String toString() {
        return "UserBatchMessage{" +
                "id='" + id + '\'' +
                ", fromUserId='" + fromUserId + '\'' +
                ", toUserIds=" + toUserIds +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                ", type='" + type + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public List<String> getToUserIds() {
        return toUserIds;
    }

    public void setToUserIds(List<String> toUserIds) {
        this.toUserIds = toUserIds;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static final class Builder {
        private String id;
        private String fromUserId;
        private List<String> toUserIds;
        private String content;
        private String status;
        private Timestamp createTime;
        private Timestamp lastUpdate;
        private String type;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder fromUserId(String val) {
            fromUserId = val;
            return this;
        }

        public Builder toUserIds(List<String> val) {
            toUserIds = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder status(String val) {
            status = val;
            return this;
        }

        public Builder createTime(Timestamp val) {
            createTime = val;
            return this;
        }

        public Builder lastUpdate(Timestamp val) {
            lastUpdate = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public UserBatchMessage build() {
            return new UserBatchMessage(this);
        }
    }
}
