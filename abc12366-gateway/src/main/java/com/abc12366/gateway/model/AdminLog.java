package com.abc12366.gateway.model;

import com.abc12366.gateway.model.bo.TableBO;

import java.util.Date;

/**
 * 操作员日志
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-09-27 2:08 PM
 * @since 1.0.0
 */
public class AdminLog extends TableBO{
    // 主键
    private String id;
    // 用户ID
    private String userId;
    // 业务ID
    private String businessUri;
    // 业务名称
    private String businessName;
    // 业务数据
    private String businessData;
    // 操作类型：POST（新增）、PUT（修改）、GET（查询）、DELETE（删除）
    private String method;
    // 操作时间
    private Date createTime;
    // 备注
    private String remark;

    // 开始日期，用于查询
    private Date startDate;
    // 结束日期，用于查询
    private Date endDate;
    // 用户名，用于查询
    private String username;
    // 昵称，用于查询
    private String nickname;

    public AdminLog() {
    }

    private AdminLog(Builder builder) {
        setId(builder.id);
        setUserId(builder.userId);
        setBusinessUri(builder.businessUri);
        setBusinessName(builder.businessName);
        setBusinessData(builder.businessData);
        setMethod(builder.method);
        setCreateTime(builder.createTime);
        setRemark(builder.remark);
        setStartDate(builder.startDate);
        setEndDate(builder.endDate);
        setUsername(builder.username);
        setNickname(builder.nickname);
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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBusinessUri() {
        return businessUri;
    }

    public void setBusinessUri(String businessUri) {
        this.businessUri = businessUri;
    }

    public String getBusinessData() {
        return businessData;
    }

    public void setBusinessData(String businessData) {
        this.businessData = businessData;
    }

    @Override
    public String toString() {
        return "AdminLog{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", businessUri='" + businessUri + '\'' +
                ", businessName='" + businessName + '\'' +
                ", businessData='" + businessData + '\'' +
                ", method='" + method + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                "} " + super.toString();
    }

    public static final class Builder {
        private String id;
        private String userId;
        private String businessUri;
        private String businessName;
        private String businessData;
        private String method;
        private Date createTime;
        private String remark;
        private Date startDate;
        private Date endDate;
        private String username;
        private String nickname;

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

        public Builder businessUri(String val) {
            businessUri = val;
            return this;
        }

        public Builder businessName(String val) {
            businessName = val;
            return this;
        }

        public Builder businessData(String val) {
            businessData = val;
            return this;
        }

        public Builder method(String val) {
            method = val;
            return this;
        }

        public Builder createTime(Date val) {
            createTime = val;
            return this;
        }

        public Builder remark(String val) {
            remark = val;
            return this;
        }

        public Builder startDate(Date val) {
            startDate = val;
            return this;
        }

        public Builder endDate(Date val) {
            endDate = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder nickname(String val) {
            nickname = val;
            return this;
        }

        public AdminLog build() {
            return new AdminLog(this);
        }
    }
}
