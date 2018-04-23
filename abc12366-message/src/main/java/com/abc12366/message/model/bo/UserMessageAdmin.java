package com.abc12366.message.model.bo;

import java.util.Date;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-10-27
 * Time: 14:27
 */
public class UserMessageAdmin {
    private String id;
    //发送人ID
    private String fromUserId;
    //接收人ID
    private String toUserId;
    //私信内容
    private String content;
    //私信状态
    private String status;

    private Date createTime;

    private Date lastUpdate;

    //私信类型
    private String type;

    //发送人用户名
    private String fromuser;

    //发送人头像图片地址
    private String fromUserPic;

    //接收人用户名
    private String touser;

    //接收人人头像图片地址
    private String toUserPic;

    public UserMessageAdmin() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFromuser() {
        return fromuser;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser;
    }

    public String getFromUserPic() {
        return fromUserPic;
    }

    public void setFromUserPic(String fromUserPic) {
        this.fromUserPic = fromUserPic;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getToUserPic() {
        return toUserPic;
    }

    public void setToUserPic(String toUserPic) {
        this.toUserPic = toUserPic;
    }

    @Override
    public String toString() {
        return "UserMessageAdmin{" +
                "id='" + id + '\'' +
                ", fromUserId='" + fromUserId + '\'' +
                ", toUserId='" + toUserId + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", lastUpdate=" + lastUpdate +
                ", type='" + type + '\'' +
                ", fromuser='" + fromuser + '\'' +
                ", fromUserPic='" + fromUserPic + '\'' +
                ", touser='" + touser + '\'' +
                ", toUserPic='" + toUserPic + '\'' +
                '}';
    }
}
