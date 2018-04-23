package com.abc12366.cms.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * ֪ͨ����
 * User: yuanluo<435720953@qq.com.com>
 * Date: 2017-07-26
 * Time: 11:06
 */
public class NoticeBO {

    private String id;

    @Size(max = 64)
    private String title;
    @Size(max = 64)
    private String author;
    private String content;
    /*0�ѷ�����1�ݸ�*/
    @NotNull
    private String status;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp releaseTime;

    private Integer count;

    //来源
    private String comefrom;


    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp lastUpdate;

    public NoticeBO() {
    }

    public NoticeBO(String id, String title, String author, String content, String status, Timestamp createTime,
                    Timestamp lastUpdate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.status = status;
        this.createTime = createTime;
        this.lastUpdate = lastUpdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExperienceRuleInsertBO{" +
                "id='" + id + '\'' +
                ",title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content=" + content +
                ", status=" + status + '\'' +
                ", createTime=" + createTime + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getComefrom() {
        return comefrom;
    }

    public void setComefrom(String comefrom) {
        this.comefrom = comefrom;
    }

    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
    }
}