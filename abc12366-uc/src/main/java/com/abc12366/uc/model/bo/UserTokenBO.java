package com.abc12366.uc.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-03-27 4:20 PM
 * @since 1.0.0
 */
public class UserTokenBO implements Serializable {

    private String id;
    private String name;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;

    public UserTokenBO(String id, String name, Date createTime, Date expireTime) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.expireTime = expireTime;
    }

    public UserTokenBO() {

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
