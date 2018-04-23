package com.abc12366.message.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户消息对象
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2017-07-27 11:21 AM
 * @since 1.0.0
 */
public class UserMessage {

	private String id;

	@NotEmpty
	@Length(min = 1, max = 64)
	private String fromUserId;

	@NotEmpty
	@Length(min = 1, max = 64)
	private String toUserId;

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

	private String fromNickname;
	private String toNickname;

	private Date startDate;
	private Date endDate;

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

	public UserMessage() {
	}

	public UserMessage(String id, String fromUserId, String toUserId, String content, String status,
			Timestamp createTime, Timestamp lastUpdate, String type, String fromNickname, String toNickname) {
		this.id = id;
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.content = content;
		this.status = status;
		this.createTime = createTime;
		this.lastUpdate = lastUpdate;
		this.type = type;
		this.fromNickname = fromNickname;
		this.toNickname = toNickname;
	}

	private UserMessage(Builder builder) {
		setId(builder.id);
		setFromUserId(builder.fromUserId);
		setToUserId(builder.toUserId);
		setContent(builder.content);
		setStatus(builder.status);
		setCreateTime(builder.createTime);
		setLastUpdate(builder.lastUpdate);
		setType(builder.type);
		setFromNickname(builder.fromNickname);
		setToNickname(builder.toNickname);
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

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
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

	public String getFromNickname() {
		return fromNickname;
	}

	public void setFromNickname(String fromNickname) {
		this.fromNickname = fromNickname;
	}

	public String getToNickname() {
		return toNickname;
	}

	public void setToNickname(String toNickname) {
		this.toNickname = toNickname;
	}

	@Override
	public String toString() {
		return "UserMessage{" + "id='" + id + '\'' + ", fromUserId='" + fromUserId + '\'' + ", toUserId='" + toUserId
				+ '\'' + ", content='" + content + '\'' + ", status='" + status + '\'' + ", createTime=" + createTime
				+ ", lastUpdate=" + lastUpdate + ", type='" + type + '\'' + ", fromNickname='" + fromNickname + '\''
				+ ", toNickname='" + toNickname + '\'' + '}';
	}

	public static final class Builder {
		private String id;
		private String fromUserId;
		private String toUserId;
		private String content;
		private String status;
		private Timestamp createTime;
		private Timestamp lastUpdate;
		private String type;
		private String fromNickname;
		private String toNickname;
		private String nickname;

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

		public Builder toUserId(String val) {
			toUserId = val;
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

		public Builder fromNickname(String val) {
			fromNickname = val;
			return this;
		}

		public Builder toNickname(String val) {
			toNickname = val;
			return this;
		}

		public Builder nickname(String val) {
			nickname = val;
			return this;
		}

		public UserMessage build() {
			return new UserMessage(this);
		}
	}
}
