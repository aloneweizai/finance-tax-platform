package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-15
 * Time: 14:59
 */
public class AdminModifyUserPhoneLogBO {
    /**
     * 主键
     */
    private String id;
    /**
     *用户ID
     */
    @NotEmpty(message = "用户ID不能为空")
    private String userId;
    /**
     *管理员ID
     */
    @NotEmpty(message = "管理员ID不能为空")
    private String adminId;
    /**
     *旧手机号码
     */
    private String oldPhone;
    /**
     *新手机号码
     */
    private String newPhone;
    /**
     *记日志时间
     */
    private Date createTime;
    /**
     *原因
     */
    private String reason;
    /**
     * 老用户名
     */
    private String oldUsername;
    /**
     *旧用户名
     */
    private String newUsername;

    public AdminModifyUserPhoneLogBO() {
    }

    public AdminModifyUserPhoneLogBO(String userId, String adminId, String oldPhone, String newPhone, String reason) {
        this.userId = userId;
        this.adminId = adminId;
        this.oldPhone = oldPhone;
        this.newPhone = newPhone;
        this.reason = reason;
    }

    public AdminModifyUserPhoneLogBO(String userId, String adminId, String oldPhone, String newPhone, String reason, String oldUsername, String newUsername) {
        this.userId = userId;
        this.adminId = adminId;
        this.oldPhone = oldPhone;
        this.newPhone = newPhone;
        this.reason = reason;
        this.oldUsername = oldUsername;
        this.newUsername = newUsername;
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

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getOldPhone() {
        return oldPhone;
    }

    public void setOldPhone(String oldPhone) {
        this.oldPhone = oldPhone;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOldUsername() {
        return oldUsername;
    }

    public void setOldUsername(String oldUsername) {
        this.oldUsername = oldUsername;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    @Override
    public String toString() {
        return "AdminModifyUserPhoneLogBO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", adminId='" + adminId + '\'' +
                ", oldPhone='" + oldPhone + '\'' +
                ", newPhone='" + newPhone + '\'' +
                ", createTime=" + createTime +
                ", reason='" + reason + '\'' +
                ", oldUsername='" + oldUsername + '\'' +
                ", newUsername='" + newUsername + '\'' +
                '}';
    }
}
