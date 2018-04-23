package com.abc12366.bangbang.model.question;

/**
 * @Author liuQi
 * @Date 2017/11/7 17:16
 * 话题统计分析
 */
public class QuestionClassifyStatistics {

    /*分类名称*/
    private String classifyName;

    /* 点赞数量 */
    private String likeNum;

    /* 评论数量 */
    private String commentNum;

    /* 回答数量 */
    private String answerNum;

    /* 提问数量 */
    private String questionNum;


    public String getClassifyName() {
        return classifyName;
    }

    public QuestionClassifyStatistics setClassifyName(String classifyName) {
        this.classifyName = classifyName;
        return this;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public QuestionClassifyStatistics setLikeNum(String likeNum) {
        this.likeNum = likeNum;
        return this;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public QuestionClassifyStatistics setCommentNum(String commentNum) {
        this.commentNum = commentNum;
        return this;
    }

    public String getAnswerNum() {
        return answerNum;
    }

    public QuestionClassifyStatistics setAnswerNum(String answerNum) {
        this.answerNum = answerNum;
        return this;
    }

    public String getQuestionNum() {
        return questionNum;
    }

    public QuestionClassifyStatistics setQuestionNum(String questionNum) {
        this.questionNum = questionNum;
        return this;
    }
}
