package com.abc12366.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * 投票题目表
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-06-08 2:57 PM
 * @since 1.0.0
 */
public class Subject {

    private String id;

    // FK
    private String voteId;

    // 题目
    @NotEmpty
    @Length(min = 2, max = 200)
    private String subject;

    // 题目形式：radio、checkbox
//    @NotEmpty
    @Length(max = 10)
    private String form;

    // 是否必填
    @NotNull
    private Boolean required;

    // 题目顺序
    @Length(min = 1, max = 999)
    private Integer sort;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp lastUpdate;

    private List<SubjectItem> itemList;

    public Subject() {
    }

    private Subject(Builder builder) {
        setId(builder.id);
        setVoteId(builder.voteId);
        setSubject(builder.subject);
        setForm(builder.form);
        setRequired(builder.required);
        setSort(builder.sort);
        setCreateTime(builder.createTime);
        setLastUpdate(builder.lastUpdate);
    }

    public List<SubjectItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SubjectItem> itemList) {
        this.itemList = itemList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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


    public static final class Builder {
        private String id;
        private String voteId;
        private String subject;
        private String form;
        private Boolean required;
        private Integer sort;
        private Timestamp createTime;
        private Timestamp lastUpdate;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder voteId(String val) {
            voteId = val;
            return this;
        }

        public Builder subject(String val) {
            subject = val;
            return this;
        }

        public Builder form(String val) {
            form = val;
            return this;
        }

        public Builder required(Boolean val) {
            required = val;
            return this;
        }

        public Builder sort(Integer val) {
            sort = val;
            return this;
        }

        public Builder createTime(Timestamp val) {
            createTime = val;
            return this;
        }

        public Builder lastUpdate(Timestamp val) {
            lastUpdate = val;
            return this;
        }

        public Subject build() {
            return new Subject(this);
        }
    }
}
