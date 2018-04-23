package com.abc12366.cms.model.questionnaire;

import java.io.Serializable;


/**
 * 问卷表
 **/
@SuppressWarnings("serial")
public class Questionnaire implements Serializable {

    /****/
    private String id;

    /**
     * 标题
     **/
    private String title;

    /**
     * 简单描述
     **/
    private String simpleDesc;

    /**
     * 状态，true：正在回收；false：暂停回收
     **/
    private String status;

    /**
     * 创建人
     **/
    private String createUser;

    /**
     * 创建时间
     **/
    private java.util.Date createTime;

    /**
     * 修改时间
     **/
    private java.util.Date updateTime;

    /**
     * 修改人
     **/
    private String updateUser;

    /**
     * 回收量
     **/
    private Integer recoveryRate;

    /**
     * 访问量
     **/
    private Integer accessRate;

    /**
     * 所属场景类别代码
     **/
    private String sceneCode;

    /**
     * 所属行业类别代码
     **/
    private String tradeCode;

    /**
     * 皮肤路径
     **/
    private String skinUrl;

    /**
     * 结束语
     **/
    private String endDesc;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSimpleDesc() {
        return this.simpleDesc;
    }

    public void setSimpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getRecoveryRate() {
        return this.recoveryRate;
    }

    public void setRecoveryRate(Integer recoveryRate) {
        this.recoveryRate = recoveryRate;
    }

    public Integer getAccessRate() {
        return this.accessRate;
    }

    public void setAccessRate(Integer accessRate) {
        this.accessRate = accessRate;
    }

    public String getSceneCode() {
        return this.sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public String getTradeCode() {
        return this.tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public String getSkinUrl() {
        return skinUrl;
    }

    public void setSkinUrl(String skinUrl) {
        this.skinUrl = skinUrl;
    }

    public String getEndDesc() {
        return endDesc;
    }

    public Questionnaire setEndDesc(String endDesc) {
        this.endDesc = endDesc;
        return this;
    }
}
