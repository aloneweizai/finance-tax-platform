package com.abc12366.cms.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-06-08 3:39 PM
 * @since 1.0.0
 */
public class SubjectItem {

    private String id;

    private String subjectId;

    @NotEmpty
    @Length(min = 1, max = 10)
    private String type;

    @Length(max = 200)
    private String item;

    @Length(max = 256)
    private String image;

    @Length(max = 4000)
    private String detail;

    @Length(min = 1, max = 999)
    private Integer sort;

    // 参与人数（number of participants）
    private Integer nop;

    private String status;

    public SubjectItem() {
    }

    public SubjectItem(String id, String subjectId, String type, String item, String image, String detail, Integer
            sort, Integer nop, String status) {
        this.id = id;
        this.subjectId = subjectId;
        this.type = type;
        this.item = item;
        this.image = image;
        this.detail = detail;
        this.sort = sort;
        this.nop = nop;
        this.status = status;
    }

    private SubjectItem(Builder builder) {
        setId(builder.id);
        setSubjectId(builder.subjectId);
        setType(builder.type);
        setItem(builder.item);
        setImage(builder.image);
        setDetail(builder.detail);
        setSort(builder.sort);
        setNop(builder.nop);
        setStatus(builder.status);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getNop() {
        return nop;
    }

    public void setNop(Integer nop) {
        this.nop = nop;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SubjectItem{" +
                "id='" + id + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", type='" + type + '\'' +
                ", item='" + item + '\'' +
                ", image='" + image + '\'' +
                ", detail='" + detail + '\'' +
                ", status='" + status + '\'' +
                ", sort=" + sort +
                ", nop=" + nop +
                '}';
    }

    public static final class Builder {
        private String id;
        private String subjectId;
        private String type;
        private String item;
        private String image;
        private String detail;
        private Integer sort;
        private Integer nop;
        private String status;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder subjectId(String val) {
            subjectId = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder item(String val) {
            item = val;
            return this;
        }

        public Builder image(String val) {
            image = val;
            return this;
        }

        public Builder detail(String val) {
            detail = val;
            return this;
        }

        public Builder sort(Integer val) {
            sort = val;
            return this;
        }

        public Builder nop(Integer val) {
            nop = val;
            return this;
        }

        public Builder status(String val) {
            status = val;
            return this;
        }

        public SubjectItem build() {
            return new SubjectItem(this);
        }
    }
}
