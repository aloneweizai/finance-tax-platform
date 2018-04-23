package com.abc12366.uc.model.admin.bo;

import java.util.Date;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-12-20
 * Time: 16:10
 */
public class YyxxLogListBO {
    /**
     * 日志id
     */
    private String id;
    /**
     *用户id
     */
    private String userId;
    /**
     *运营消息id
     */
    private String messageId;
    /**
     *消息类型，web-站内，wechat-微信，message-短信
     */
    private String type;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *用户昵称
     */
    private String nickName;

    public YyxxLogListBO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "YyxxLogListBO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", messageId='" + messageId + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
