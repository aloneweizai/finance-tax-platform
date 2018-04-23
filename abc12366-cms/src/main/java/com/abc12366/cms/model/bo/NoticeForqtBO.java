package com.abc12366.cms.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * ֪ͨ����
 * User: yuanluo<435720953@qq.com.com>
 * Date: 2017-07-26
 * Time: 11:06
 */
public class NoticeForqtBO {

    private String id;

    @Size(max = 64)
    private String title;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp releaseTime;

    public NoticeForqtBO() {
    }

    public NoticeForqtBO(String id, String title,Timestamp releaseTime) {
        this.id = id;
        this.title = title;
        this.releaseTime = releaseTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ExperienceRuleInsertBO{" +
                "id='" + id + '\'' +
                ",title='" + title + '\'' +
                ", releaseTime=" + releaseTime +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
    }
}