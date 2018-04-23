package com.abc12366.uc.model;

import java.util.Date;

/**
 * @author liuguiyao<435720953@qq.com.com>
 * @create 2017-05-15 10:18 PM
 * @since 2.0.0
 */
public class UserHnds {
    private String id;
    private String userId;
    private String username;
    private String subuser;
    private String nsrmc;
    private String nsrsbh;
    private String djxh;
    private String shxydm;
    private String swjgMc;
    private String swjgDm;
    private boolean status;
    private Date createTime;
    private Date lastUpdate;

    public UserHnds() {
    }

    public UserHnds(String id, String userId, String username, String subuser, String nsrmc, String nsrsbh, String
            djxh, String shxydm, String swjgMc, String swjgDm, boolean status, Date createTime, Date lastUpdate) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.subuser = subuser;
        this.nsrmc = nsrmc;
        this.nsrsbh = nsrsbh;
        this.djxh = djxh;
        this.shxydm = shxydm;
        this.swjgMc = swjgMc;
        this.swjgDm = swjgDm;
        this.status = status;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubuser() {
        return subuser;
    }

    public void setSubuser(String subuser) {
        this.subuser = subuser;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getDjxh() {
        return djxh;
    }

    public void setDjxh(String djxh) {
        this.djxh = djxh;
    }

    public String getShxydm() {
        return shxydm;
    }

    public void setShxydm(String shxydm) {
        this.shxydm = shxydm;
    }

    public String getSwjgMc() {
        return swjgMc;
    }

    public void setSwjgMc(String swjgMc) {
        this.swjgMc = swjgMc;
    }

    public String getSwjgDm() {
        return swjgDm;
    }

    public void setSwjgDm(String swjgDm) {
        this.swjgDm = swjgDm;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
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
        return "UserHnds{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", subuser='" + subuser + '\'' +
                ", nsrmc='" + nsrmc + '\'' +
                ", nsrsbh='" + nsrsbh + '\'' +
                ", djxh='" + djxh + '\'' +
                ", shxydm='" + shxydm + '\'' +
                ", swjgMc='" + swjgMc + '\'' +
                ", swjgDm='" + swjgDm + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
