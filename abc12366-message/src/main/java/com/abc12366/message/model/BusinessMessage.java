package com.abc12366.message.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Timestamp;

/**
 * 业务消息对象
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-07-27 11:21 AM
 * @since 1.0.0
 */
public class BusinessMessage {

    private String id;

    @NotEmpty
    @Length(min = 1, max = 64)
    private String userId;

    @NotEmpty
    @Length(min = 1, max = 64)
    private String businessId;

    @NotEmpty
    @Length(min = 1, max = 500)
    private String content;

    @Length(min = 1, max = 1)
    private String status;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp lastUpdate;

    @Length(min = 1, max = 32)
    private String type;

    //链接地址
    private String url;

    //消息业务类型
    private String busiType;
    
    private String dateStr;



    public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public BusinessMessage() {
    }

    public BusinessMessage(String id, String userId, String businessId, String content, String status, Timestamp
            createTime, Timestamp lastUpdate, String type, String url, String busiType) {
        this.id = id;
        this.userId = userId;
        this.businessId = businessId;
        this.content = content;
        this.status = status;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
        this.type = type;
        this.url = url;
        this.busiType = busiType;
    }

    private BusinessMessage(Builder builder) {
        setId(builder.id);
        setUserId(builder.userId);
        setBusinessId(builder.businessId);
        setContent(builder.content);
        setStatus(builder.status);
        setCreateTime(builder.createTime);
        setLastUpdate(builder.lastUpdate);
        setType(builder.type);
        setUrl(builder.url);
        setBusiType(builder.busiType);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public static final class Builder {
        private String id;
        private String userId;
        private String businessId;
        private String content;
        private String status;
        private Timestamp createTime;
        private Timestamp lastUpdate;
        private String type;
        private String url;
        private String busiType;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public Builder businessId(String val) {
            businessId = val;
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

        public Builder url(String val) {
            url = val;
            return this;
        }

        public Builder busiType(String val) {
            busiType = val;
            return this;
        }

        public BusinessMessage build() {
            return new BusinessMessage(this);
        }
    }
}
