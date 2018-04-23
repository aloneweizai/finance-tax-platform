package com.abc12366.cms.model.questionnaire;

import java.io.Serializable;


/**
 * 选项表
 **/
@SuppressWarnings("serial")
public class Option implements Serializable {

    /**
     * 选项ID
     **/
    private String id;

    /**
     * 题目ID
     **/
    private String subjectsId;

    /**
     * 答案内容
     **/
    private String optionString;

    /**
     * 序号
     **/
    private Integer sequence;

    /**
     * 选项状态，true:正常，false:删除
     **/
    private Boolean status;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOptionString() {
        return this.optionString;
    }

    public void setOptionString(String optionString) {
        this.optionString = optionString;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getSubjectsId() {
        return subjectsId;
    }

    public void setSubjectsId(String subjectsId) {
        this.subjectsId = subjectsId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
