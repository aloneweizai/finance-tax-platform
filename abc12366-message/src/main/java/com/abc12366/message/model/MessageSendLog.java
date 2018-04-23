package com.abc12366.message.model;

import java.util.Date;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-10
 * Time: 18:19
 */
public class MessageSendLog {
    private String id;
    private String sendchanel;
    private String phone;
    private String biztype;
    private String sendinfo;
    private Date sendtime;
    private String sendstatus;
    private String failcode;
    private String failcause;
    private Date logtime;
    private String bizId;

    public MessageSendLog() {
    }

    public MessageSendLog(String sendchanel, String phone, String biztype, String sendinfo, String sendstatus, String failcode, String failcause) {
        this.sendchanel = sendchanel;
        this.phone = phone;
        this.biztype = biztype;
        this.sendinfo = sendinfo;
        this.sendstatus = sendstatus;
        this.failcode = failcode;
        this.failcause = failcause;
    }

    public MessageSendLog(String id, String sendchanel, String phone, String biztype, String sendinfo, Date sendtime, String sendstatus, String failcode, String failcause, Date logtime, String bizId) {
        this.id = id;
        this.sendchanel = sendchanel;
        this.phone = phone;
        this.biztype = biztype;
        this.sendinfo = sendinfo;
        this.sendtime = sendtime;
        this.sendstatus = sendstatus;
        this.failcode = failcode;
        this.failcause = failcause;
        this.logtime = logtime;
        this.bizId = bizId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendchanel() {
        return sendchanel;
    }

    public void setSendchanel(String sendchanel) {
        this.sendchanel = sendchanel;
    }

    public String getBiztype() {
        return biztype;
    }

    public void setBiztype(String biztype) {
        this.biztype = biztype;
    }

    public String getSendinfo() {
        return sendinfo;
    }

    public void setSendinfo(String sendinfo) {
        this.sendinfo = sendinfo;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getSendstatus() {
        return sendstatus;
    }

    public void setSendstatus(String sendstatus) {
        this.sendstatus = sendstatus;
    }

    public String getFailcode() {
        return failcode;
    }

    public void setFailcode(String failcode) {
        this.failcode = failcode;
    }

    public String getFailcause() {
        return failcause;
    }

    public void setFailcause(String failcause) {
        this.failcause = failcause;
    }

    public Date getLogtime() {
        return logtime;
    }

    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "MessageSendLog{" +
                "id='" + id + '\'' +
                ", sendchanel='" + sendchanel + '\'' +
                ", phone='" + phone + '\'' +
                ", biztype='" + biztype + '\'' +
                ", sendinfo='" + sendinfo + '\'' +
                ", sendtime=" + sendtime +
                ", sendstatus='" + sendstatus + '\'' +
                ", failcode='" + failcode + '\'' +
                ", failcause='" + failcause + '\'' +
                ", logtime=" + logtime +
                '}';
    }
}
