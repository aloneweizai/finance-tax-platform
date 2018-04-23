package com.abc12366.uc.model;

import java.util.Date;

/**
 * Created by lsz on 2017/8/10.
 */
public class VipPrivilegeLevel {
    /**id*/
    private String id;
    /**'等级id'*/
    private String levelId;
    /**'权益id'*/
    private String privilegeId;
    /**'val1'*/
    private String val1;
    /**'val2'*/
    private String val2;
    /**'val3'*/
    private String val3;
    /**'val4'*/
    private String val4;
    /**''描述''*/
    private String description;

    /**''数据状态''*/
    private boolean status;
    /**修改时间*/
    private Date lastUpdate;
    /**创建时间*/
    private Date createTime;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPrivilegeId() {
        return privilegeId;
    }
    public void setPrivilegeId(String privilegeId) {
        this.privilegeId = privilegeId;
    }
    public String getLevelId() {
        return levelId;
    }
    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }
    public String getVal1() {
        return val1;
    }
    public void setVal1(String val1) {
        this.val1 = val1;
    }
    public String getVal2() {
        return val2;
    }
    public void setVal2(String val2) {
        this.val2 = val2;
    }
    public String getVal3() {
        return val3;
    }
    public void setVal3(String val3) {
        this.val3 = val3;
    }
    public String getVal4() {
        return val4;
    }
    public void setVal4(String val4) {
        this.val4 = val4;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean Status() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Override
    public String toString() {
        return "VipPrivilegeLevel [id=" + id + ", privilegeId=" + privilegeId + ", val1=" + val1 + ", val2=" + val2
                + ", val3=" + val3 + ", val4=" + val4 + ", description=" + description + ", status=" + status
                + ", lastUpdate=" + lastUpdate + ", createTime=" + createTime + "]";
    }



}
