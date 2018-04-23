package com.abc12366.bangbang.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户日志新增BO
 *
 * @author lingsuzhi <554600654@qq.com.com>
 * @create 2017-08-16
 */
public class SystemRecordInsertBO {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 会话ID
     */
    private String sessionId;
    /**
     * 浏览时间
     */
    @NotNull
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date browseDate;
    /**
     * 浏览页面TITLE
     */
    @Length(max = 100)
    private String pageTitle;
    /**
     * 浏览页面路径
     */
    @NotEmpty
    @Length(max = 100)
    private String pageUrl;
    /**
     * REFERER路径
     */
    private String referer;
    /**
     * 停留时长，单位：秒
     */
    private String stayLong;
    /**
     * 操作功能
     */
    private String feature;
    /**
     * 使用系统
     */
    private String appName;
    /**
     * 访问IP
     */
    private String ip;
    /**
     * 访问地点
     */
    private String location;
    /**
     * 浏览器类型
     */
    private String browseType;
    /**
     * 浏览器版本
     */
    private String browseVersion;
    /**
     * 操作系统类型
     */
    private String os;
    /**
     * 设备类型
     */
    private String device;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 规则代码：经验值规则代码
     */
    @Length(max = 20)
    private String ruleCode;

    /**
     * 纳税人识别号
     */
    @Length(max = 32)
    private String nsrsbh;

    /**
     * 一级菜单
     */
    @Length(max = 15)
    private String menua;

    /**
     * 二级菜单
     */
    @Length(max = 30)
    private String menub;

    /**
     * 三级菜单
     */
    @Length(max = 30)
    private String menuc;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getBrowseDate() {
        return browseDate;
    }

    public void setBrowseDate(Date browseDate) {
        this.browseDate = browseDate;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getStayLong() {
        return stayLong;
    }

    public void setStayLong(String stayLong) {
        this.stayLong = stayLong;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBrowseType() {
        return browseType;
    }

    public void setBrowseType(String browseType) {
        this.browseType = browseType;
    }

    public String getBrowseVersion() {
        return browseVersion;
    }

    public void setBrowseVersion(String browseVersion) {
        this.browseVersion = browseVersion;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getMenua() {
        return menua;
    }

    public void setMenua(String menua) {
        this.menua = menua;
    }

    public String getMenub() {
        return menub;
    }

    public void setMenub(String menub) {
        this.menub = menub;
    }

    public String getMenuc() {
        return menuc;
    }

    public void setMenuc(String menuc) {
        this.menuc = menuc;
    }

    @Override
    public String toString() {
        return "SystemRecordInsertBO{" +
                "userId='" + userId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", browseDate=" + browseDate +
                ", pageTitle='" + pageTitle + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                ", referer='" + referer + '\'' +
                ", stayLong='" + stayLong + '\'' +
                ", feature='" + feature + '\'' +
                ", appName='" + appName + '\'' +
                ", ip='" + ip + '\'' +
                ", location='" + location + '\'' +
                ", browseType='" + browseType + '\'' +
                ", browseVersion='" + browseVersion + '\'' +
                ", os='" + os + '\'' +
                ", device='" + device + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", ruleCode='" + ruleCode + '\'' +
                ", nsrsbh='" + nsrsbh + '\'' +
                ", menua='" + menua + '\'' +
                ", menub='" + menub + '\'' +
                ", menuc='" + menuc + '\'' +
                '}';
    }
}
