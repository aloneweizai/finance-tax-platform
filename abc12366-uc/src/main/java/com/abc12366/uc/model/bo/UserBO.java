package com.abc12366.uc.model.bo;

import java.util.Date;

/**
 * 用户实体类
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-02-20 4:14 PM
 * @since 1.0.0
 */
public class UserBO {
    private String id;
    private String username;
    private String phone;
    private String password;
    private String regMail;
    private String regIP;
    private String salt;
    private String nickname;
    private Boolean status;
    private Date createTime;
    private Date lastUpdate;
    private String userPicturePath;
    private String maxUserPicturePath;
    private String midUserPicturePath;
    private String minUserPicturePath;
    private Integer points;
    private Integer exp;
    private String vipLevel;
    private String vipLevelName;
    private String realName;
    private String level;
    private String levelName;
    private Integer usernameModifiedTimes;
    private Date vipExpireDate;
    private String vipImage;
    private String wxopenid;
    private String wxheadimg;
    private String wxnickname;
    /**
     * 老系统中的ID
     */
    private String oldId;

    public String getWxopenid() {
		return wxopenid;
	}

	public void setWxopenid(String wxopenid) {
		this.wxopenid = wxopenid;
	}

	public String getWxheadimg() {
		return wxheadimg;
	}

	public void setWxheadimg(String wxheadimg) {
		this.wxheadimg = wxheadimg;
	}

	public String getWxnickname() {
		return wxnickname;
	}

	public void setWxnickname(String wxnickname) {
		this.wxnickname = wxnickname;
	}

	public UserBO() {
    }

    public Integer getUsernameModifiedTimes() {
        return usernameModifiedTimes;
    }

    public void setUsernameModifiedTimes(Integer usernameModifiedTimes) {
        this.usernameModifiedTimes = usernameModifiedTimes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegMail() {
        return regMail;
    }

    public void setRegMail(String regMail) {
        this.regMail = regMail;
    }

    public String getRegIP() {
        return regIP;
    }

    public void setRegIP(String regIP) {
        this.regIP = regIP;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }

    public String getMaxUserPicturePath() {
        return maxUserPicturePath;
    }

    public void setMaxUserPicturePath(String maxUserPicturePath) {
        this.maxUserPicturePath = maxUserPicturePath;
    }

    public String getMidUserPicturePath() {
        return midUserPicturePath;
    }

    public void setMidUserPicturePath(String midUserPicturePath) {
        this.midUserPicturePath = midUserPicturePath;
    }

    public String getMinUserPicturePath() {
        return minUserPicturePath;
    }

    public void setMinUserPicturePath(String minUserPicturePath) {
        this.minUserPicturePath = minUserPicturePath;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getVipExpireDate() {
        return vipExpireDate;
    }

    public void setVipExpireDate(Date vipExpireDate) {
        this.vipExpireDate = vipExpireDate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getVipImage() {
        return vipImage;
    }

    public void setVipImage(String vipImage) {
        this.vipImage = vipImage;
    }

    public String getVipLevelName() {
        return vipLevelName;
    }

    public void setVipLevelName(String vipLevelName) {
        this.vipLevelName = vipLevelName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getOldId() {
        return oldId;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    @Override
    public String toString() {
        return "UserBO{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", regMail='" + regMail + '\'' +
                ", regIP='" + regIP + '\'' +
                ", salt='" + salt + '\'' +
                ", nickname='" + nickname + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                ", userPicturePath='" + userPicturePath + '\'' +
                ", maxUserPicturePath='" + maxUserPicturePath + '\'' +
                ", midUserPicturePath='" + midUserPicturePath + '\'' +
                ", minUserPicturePath='" + minUserPicturePath + '\'' +
                ", points=" + points +
                ", exp=" + exp +
                ", vipLevel='" + vipLevel + '\'' +
                ", vipLevelName='" + vipLevelName + '\'' +
                ", realName='" + realName + '\'' +
                ", level='" + level + '\'' +
                ", levelName='" + levelName + '\'' +
                ", usernameModifiedTimes=" + usernameModifiedTimes +
                ", vipExpireDate=" + vipExpireDate +
                ", vipImage='" + vipImage + '\'' +
                ", wxopenid='" + wxopenid + '\'' +
                ", wxheadimg='" + wxheadimg + '\'' +
                ", wxnickname='" + wxnickname + '\'' +
                ", oldId='" + oldId + '\'' +
                '}';
    }
}
