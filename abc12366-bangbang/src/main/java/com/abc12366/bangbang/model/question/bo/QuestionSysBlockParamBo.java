package com.abc12366.bangbang.model.question.bo;

/**
 * @Author liuQi
 * @Date 2017/10/23 16:12
 */
public class QuestionSysBlockParamBo {

    private String content;

    private String status;

    private String beginTime;

    private String endTime;

    public String getContent() {
        return content;
    }

    public QuestionSysBlockParamBo setContent(String content) {
        this.content = content;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public QuestionSysBlockParamBo setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public QuestionSysBlockParamBo setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public QuestionSysBlockParamBo setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }
}
