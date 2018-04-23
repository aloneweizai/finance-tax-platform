package com.abc12366.uc.model;

import java.util.Date;


/**
 * @author liuguiyao<435720953@qq.com.com>
 * @create 2017-05-15 10:18 PM
 * @since 2.0.0
 */
public class UserDzsb {
    /**
     * 绑定状态
     */
    boolean status;
    /**
     *绑定关系数据主键
     */
    private String id;
    /**
     *用户ID
     */
    private String userId;
    /**
     *登记序号
     */
    private String djxh;
    /**
     *纳税人识别号
     */
    private String nsrsbh;
    /**
     *纳税人名称
     */
    private String nsrmc;
    /**
     *社会信用代码
     */
    private String shxydm;
    /**
     *税务机关名称
     */
    private String swjgMc;
    /**
     *税务机关代码
     */
    private String swjgDm;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *最后更新时间
     */
    private Date lastUpdate;
    /**
     *软件到期日
     */
    private Date expireTime;
    /**
     *延期到期日
     */
    private Date expandExpireTime;
    /**
     *法人名称
     */
    private String frmc;
    /**
     *法人证件号
     */
    private String frzjh;
    /**
     *最后登陆时间
     */
    private Date lastLoginTime;
    /**
     *纳税人类型
     */
    private String nsrlx;
    /**
     *是否个体建账户
     */
    private String sfgtjzh;
    /**
     *税务登记日期
     */
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

	public UserDzsb() {
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
        return "UserDzsb{" +
                "status=" + status +
                ", id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", djxh='" + djxh + '\'' +
                ", nsrsbh='" + nsrsbh + '\'' +
                ", nsrmc='" + nsrmc + '\'' +
                ", shxydm='" + shxydm + '\'' +
                ", swjgMc='" + swjgMc + '\'' +
                ", swjgDm='" + swjgDm + '\'' +
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
