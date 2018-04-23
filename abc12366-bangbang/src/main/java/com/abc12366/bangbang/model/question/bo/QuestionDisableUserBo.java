package com.abc12366.bangbang.model.question.bo;

/**
 * @Author liuQi
 * @Date 2017/10/18 17:26
 */
public class QuestionDisableUserBo {

    /**前端用户id**/
    private String userId;

    /**前端用户名**/
    private String username;

    /**提问拉黑数**/
    private String questionDisableCnt;

    /**回答拉黑数**/
    private String answerDisableCnt;

    /**评论拉黑数**/
    private String commentDisableCnt;

    /**是否禁言**/
    private Boolean isDisable;

    public String getUserId() {
        return userId;
    }

    public QuestionDisableUserBo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public QuestionDisableUserBo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getQuestionDisableCnt() {
        return questionDisableCnt;
    }

    public QuestionDisableUserBo setQuestionDisableCnt(String questionDisableCnt) {
        this.questionDisableCnt = questionDisableCnt;
        return this;
    }

    public String getAnswerDisableCnt() {
        return answerDisableCnt;
    }

    public QuestionDisableUserBo setAnswerDisableCnt(String answerDisableCnt) {
        this.answerDisableCnt = answerDisableCnt;
        return this;
    }

    public String getCommentDisableCnt() {
        return commentDisableCnt;
    }

    public QuestionDisableUserBo setCommentDisableCnt(String commentDisableCnt) {
        this.commentDisableCnt = commentDisableCnt;
        return this;
    }

    public Boolean getIsDisable() {
        return isDisable;
    }

    public QuestionDisableUserBo setIsDisable(Boolean isDisable) {
        this.isDisable = isDisable;
        return this;
    }
}
