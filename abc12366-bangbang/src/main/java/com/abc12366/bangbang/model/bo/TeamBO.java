package com.abc12366.bangbang.model.bo;

import java.util.Date;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-29
 * Time: 10:14
 */
public class TeamBO {
    private String id;
    private String teamName;
    private String introduction;
    private String status;
    private int members;
    private Date createTime;
    private Date lastUpdate;
    private String teamPicturePath;
    private String maxPicturePath;
    private String midPicturePath;
    private String minPicturePath;
    private String acceptedCount;

    public TeamBO() {
    }

    public String getAcceptedCount() {
        return acceptedCount;
    }

    public void setAcceptedCount(String acceptedCount) {
        this.acceptedCount = acceptedCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
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

    public String getTeamPicturePath() {
        return teamPicturePath;
    }

    public void setTeamPicturePath(String teamPicturePath) {
        this.teamPicturePath = teamPicturePath;
    }

    public String getMaxPicturePath() {
        return maxPicturePath;
    }

    public void setMaxPicturePath(String maxPicturePath) {
        this.maxPicturePath = maxPicturePath;
    }

    public String getMidPicturePath() {
        return midPicturePath;
    }

    public void setMidPicturePath(String midPicturePath) {
        this.midPicturePath = midPicturePath;
    }

    public String getMinPicturePath() {
        return minPicturePath;
    }

    public void setMinPicturePath(String minPicturePath) {
        this.minPicturePath = minPicturePath;
    }
}
