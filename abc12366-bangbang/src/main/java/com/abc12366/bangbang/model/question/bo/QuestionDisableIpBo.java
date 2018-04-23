package com.abc12366.bangbang.model.question.bo;

/**
 * @Author liuQi
 * @Date 2017/10/18 17:52
 */
public class QuestionDisableIpBo {

    /**前端ip**/
    private String ip;

    /**提问拉黑数**/
    private String questionDisableCnt;

    /**回答拉黑数**/
    private String answerDisableCnt;

    /**评论拉黑数**/
    private String commentDisableCnt;

    /**是否禁言**/
    private Boolean isDisable;

    public String getIp() {
        return ip;
    }

    public QuestionDisableIpBo setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getQuestionDisableCnt() {
        return questionDisableCnt;
    }

    public QuestionDisableIpBo setQuestionDisableCnt(String questionDisableCnt) {
        this.questionDisableCnt = questionDisableCnt;
        return this;
    }

    public String getAnswerDisableCnt() {
        return answerDisableCnt;
    }

    public QuestionDisableIpBo setAnswerDisableCnt(String answerDisableCnt) {
        this.answerDisableCnt = answerDisableCnt;
        return this;
    }

    public String getCommentDisableCnt() {
        return commentDisableCnt;
    }

    public QuestionDisableIpBo setCommentDisableCnt(String commentDisableCnt) {
        this.commentDisableCnt = commentDisableCnt;
        return this;
    }

    public Boolean getIsDisable() {
        return isDisable;
    }

    public QuestionDisableIpBo setIsDisable(Boolean isDisable) {
        this.isDisable = isDisable;
        return this;
    }
}
