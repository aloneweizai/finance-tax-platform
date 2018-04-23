package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-25
 * Time: 16:22
 */
public class UserDzsbBO {
    private String id;
    @NotEmpty
    private String userId;
    private String djxh;
    private String nsrsbh;
    private String nsrmc;
    private String shxydm;
    private String swjgMc;
    private String swjgDm;
    @NotNull
    private Boolean status;
    private Date createTime;
    private Date lastUpdate;
    private Date expireTime;
    private Date expandExpireTime;
    private String frmc;
    private String frzjh;
    private Date lastLoginTime;
    private String nsrlx;
    private String sfgtjzh;
    private String djrq;
    private String bdgroup;
    private String remark;

    public String getBdgroup() {
		return bdgroup;
	}

	public void setBdgroup(String bdgroup) {
		this.bdgroup = bdgroup;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public UserDzsbBO() {
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

    public String getDjxh() {
        return djxh;
    }

    public void setDjxh(String djxh) {
        this.djxh = djxh;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
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

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getExpandExpireTime() {
        return expandExpireTime;
    }

    public void setExpandExpireTime(Date expandExpireTime) {
        this.expandExpireTime = expandExpireTime;
    }

    public String getFrmc() {
        return frmc;
    }

    public void setFrmc(String frmc) {
        this.frmc = frmc;
    }

    public String getFrzjh() {
        return frzjh;
    }

    public void setFrzjh(String frzjh) {
        this.frzjh = frzjh;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getNsrlx() {
        return nsrlx;
    }

    public void setNsrlx(String nsrlx) {
        this.nsrlx = nsrlx;
    }

    public String getSfgtjzh() {
        return sfgtjzh;
    }

    public void setSfgtjzh(String sfgtjzh) {
        this.sfgtjzh = sfgtjzh;
    }

    public String getDjrq() {
        return djrq;
    }

    public void setDjrq(String djrq) {
        this.djrq = djrq;
    }

    @Override
    public String toString() {
        return "UserDzsbBO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", djxh='" + djxh + '\'' +
                ", nsrsbh='" + nsrsbh + '\'' +
                ", nsrmc='" + nsrmc + '\'' +
                ", shxydm='" + shxydm + '\'' +
                ", swjgMc='" + swjgMc + '\'' +
                ", swjgDm='" + swjgDm + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                ", expireTime=" + expireTime +
                ", expandExpireTime=" + expandExpireTime +
                ", frmc='" + frmc + '\'' +
                ", frzjh='" + frzjh + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", nsrlx='" + nsrlx + '\'' +
                ", sfgtjzh='" + sfgtjzh + '\'' +
                ", djrq='" + djrq + '\'' +
                '}';
    }
}
