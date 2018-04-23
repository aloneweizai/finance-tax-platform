package com.abc12366.uc.model.weixin.bo.qrcode;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 微信二维码
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-29 10:54 AM
 * @since 1.0.0
 */
public class Qrcode {

    private String id;

    @NotEmpty
    @Length(min = 1, max = 50)
    private String name;

    @Length(max = 500)
    private String description;

    private String ticket;
    private String image;

    @Length(max = 1)
    private String type;

    @Length(max = 500)
    private String content;
    private Boolean status;
    private Date createTime;
    private Date lastUpdate;

    public Qrcode() {
    }

    public Qrcode(String id, String name, String description, String ticket, String image, String type, String
            content, Boolean status, Date createTime, Date lastUpdate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ticket = ticket;
        this.image = image;
        this.type = type;
        this.content = content;
        this.status = status;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
    }

    private Qrcode(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setDescription(builder.description);
        setTicket(builder.ticket);
        setImage(builder.image);
        setType(builder.type);
        setContent(builder.content);
        setStatus(builder.status);
        setCreateTime(builder.createTime);
        setLastUpdate(builder.lastUpdate);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Qrcode{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ticket='" + ticket + '\'' +
                ", image='" + image + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public static final class Builder {
        private String id;
        private String name;
        private String description;
        private String ticket;
        private String image;
        private String type;
        private String content;
        private Boolean status;
        private Date createTime;
        private Date lastUpdate;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder ticket(String val) {
            ticket = val;
            return this;
        }

        public Builder image(String val) {
            image = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder status(Boolean val) {
            status = val;
            return this;
        }

        public Builder createTime(Date val) {
            createTime = val;
            return this;
        }

        public Builder lastUpdate(Date val) {
            lastUpdate = val;
            return this;
        }

        public Qrcode build() {
            return new Qrcode(this);
        }
    }
}
