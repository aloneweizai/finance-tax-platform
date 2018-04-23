package com.abc12366.bangbang.model.bo;

import com.abc12366.bangbang.model.curriculum.bo.CurriculumLecturerBo;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * 用户关注讲师表
 **/
@SuppressWarnings("serial")
public class FollowLecturerBO implements Serializable {

    /**
     * 主键
     **/
    private String id;

    /**
     * 用户ID
     **/
    @NotEmpty
    @Size(min = 6, max = 64)
    private String userId;

    /**
     * 被关注讲师ID
     **/
    @NotEmpty
    @Size(min = 6, max = 64)
    private String lecturerId;

    /**
     * 关注状态：0-取消关注，1-已关注
     **/
    private Boolean status;

    /**
     * 创建时间
     **/
    private java.util.Date createTime;

    /**
     * 更新时间
     **/
    private java.util.Date lastUpdate;

    private CurriculumLecturerBo curriculumLecturer;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturerId() {
        return this.lecturerId;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setLastUpdate(java.util.Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public java.util.Date getLastUpdate() {
        return this.lastUpdate;
    }

    public CurriculumLecturerBo getCurriculumLecturer() {
        return curriculumLecturer;
    }

    public void setCurriculumLecturer(CurriculumLecturerBo curriculumLecturer) {
        this.curriculumLecturer = curriculumLecturer;
    }
}
