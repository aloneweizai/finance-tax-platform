package com.abc12366.uc.model.admin.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-18
 * Time: 15:07
 */
public class OperateMessageUpdateBO {
    @NotEmpty(message = "运营消息ID不能为空")
    private String id;
    @Size(max = 150 ,message = "运营消息名称长度不能超过150")
    private String taskname;
    private Boolean web;
    private Boolean wechat;
    private Boolean message;
    @Size(max = 800 ,message = "运营消息内容长度不能超过800")
    private String content;
    @Size(max = 300 ,message = "消息链接长度不能超过300")
    private String url;
    @Size(max = 1 ,message = "目标人群长度不能超过1")
    private String target;
    @Size(max = 1 ,message = "推送时间长度不能超过1")
    private String sendTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @Size(max = 1 ,message = "状态长度不能超过1")
    private String status;
    private Date createTime;
    private Date lastUpdate;
    @Size(max = 10 ,message = "区域操作符长度不能超过10")
    private String areaOper;
    @Size(max = 200 ,message = "区域ID长度不能超过200")
    private String areaIds;
    @Size(max = 10 ,message = "标签操作符长度不能超过10")
    private String tagOper;
    @Size(max = 400 ,message = "标签ID长度不能超过400")
    private String tagIds;
    @Size(max = 10 ,message = "注册时间操作符长度不能超过10")
    private String regTimeOper;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date regStartTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date regEndTime;
    private String userIds;
    @Size(max = 1 ,message = "频率长度不能超过1")
    private String rate;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "OperateMessageBO{" +
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
