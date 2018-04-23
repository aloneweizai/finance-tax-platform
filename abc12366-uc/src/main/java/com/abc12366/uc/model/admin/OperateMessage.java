package com.abc12366.uc.model.admin;

import java.util.Date;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-18
 * Time: 15:28
 */
public class OperateMessage {
    private String id;
    private String taskname;
    private Boolean web;
    private Boolean wechat;
    private Boolean message;
    private String content;
    private String url;
    private String target;
    private String sendTime;
    private Date startTime;
    private Date endTime;
    private String status;
    private Date createTime;
    private Date lastUpdate;
    private String areaOper;
    private String areaIds;
    private String tagOper;
    private String tagIds;
    private String regTimeOper;
    private Date regStartTime;
    private Date regEndTime;
    private String userIds;
    private String rate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public Boolean getWeb() {
        return web;
    }

    public void setWeb(Boolean web) {
        this.web = web;
    }

    public Boolean getWechat() {
        return wechat;
    }

    public void setWechat(Boolean wechat) {
        this.wechat = wechat;
    }

    public Boolean getMessage() {
        return message;
    }

    public void setMessage(Boolean message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getAreaOper() {
        return areaOper;
    }

    public void setAreaOper(String areaOper) {
        this.areaOper = areaOper;
    }

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
    }

    public String getTagOper() {
        return tagOper;
    }

    public void setTagOper(String tagOper) {
        this.tagOper = tagOper;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getRegTimeOper() {
        return regTimeOper;
    }

    public void setRegTimeOper(String regTimeOper) {
        this.regTimeOper = regTimeOper;
    }

    public Date getRegStartTime() {
        return regStartTime;
    }

    public void setRegStartTime(Date regStartTime) {
        this.regStartTime = regStartTime;
    }

    public Date getRegEndTime() {
        return regEndTime;
    }

    public void setRegEndTime(Date regEndTime) {
        this.regEndTime = regEndTime;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "OperateMessage{" +
                "id='" + id + '\'' +
                ", taskname='" + taskname + '\'' +
                ", web=" + web +
                ", wechat=" + wechat +
                ", message=" + message +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", target='" + target + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                ", areaOper='" + areaOper + '\'' +
                ", areaIds='" + areaIds + '\'' +
                ", tagOper='" + tagOper + '\'' +
                ", tagIds='" + tagIds + '\'' +
                ", regTimeOper='" + regTimeOper + '\'' +
                ", regStartTime=" + regStartTime +
                ", regEndTime=" + regEndTime +
                ", userIds='" + userIds + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
