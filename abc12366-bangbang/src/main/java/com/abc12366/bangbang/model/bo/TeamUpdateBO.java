package com.abc12366.bangbang.model.bo;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-29
 * Time: 11:51
 */
public class TeamUpdateBO {
    @Size(max = 64)
    private String teamName;
    private String introduction;
    @Size(max = 1)
    private String status;
    @Min(0)
    private Integer members;
    @Size(max = 200)
    private String teamPicturePath;
    @Size(max = 200)
    private String maxPicturePath;
    @Size(max = 200)
    private String midPicturePath;
    @Size(max = 200)
    private String minPicturePath;

    public TeamUpdateBO() {
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

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
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
