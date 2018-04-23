package com.abc12366.bangbang.model.question.bo;

import java.util.Date;

/**
 * @Author liuQi
 * @Date 2017/9/22 18:07
 */
public class QuestionHeadmanBo {

    /**PK**/
    private String id;

    /**掌门人名称**/
    private String name;

    /**掌门人电话**/
    private String phone;

    /**自我介绍**/
    private String introduce;

    /**申请理由**/
    private String applyReason;

    /**备注**/
    private String remark;

    /**申请时间**/
    private java.util.Date createTime;

    /**掌门人登录用户uc_user id**/
    private String userId;

    /**掌门人负责的分类代码**/
    private String classifyCode;

    /**掌门人负责的分类名称**/
    private String classifyName;

    /**掌门人所属帮派id**/
    private String factionId;

    /**掌门人所属帮派Name**/
    private String factionName;

    /**状态（apply:申请,success:通过,refuse:拒绝）**/
    private String status;

    /**修改时间**/
    private java.util.Date updateTime;

    /**后台操作人**/
    private String updateAdmin;

    /**提问数**/
    private Integer questionNum;

    /**回答数**/
    private Integer answerNum;

    /**评论数**/
    private Integer commentNum;


    public String getId() {
        return id;
    }

    public QuestionHeadmanBo setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public QuestionHeadmanBo setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public QuestionHeadmanBo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public QuestionHeadmanBo setApplyReason(String applyReason) {
        this.applyReason = applyReason;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public QuestionHeadmanBo setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public QuestionHeadmanBo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public QuestionHeadmanBo setStatus(String status) {
        this.status = status;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public QuestionHeadmanBo setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getUpdateAdmin() {
        return updateAdmin;
    }

    public QuestionHeadmanBo setUpdateAdmin(String updateAdmin) {
        this.updateAdmin = updateAdmin;
        return this;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public QuestionHeadmanBo setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
        return this;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public QuestionHeadmanBo setClassifyName(String classifyName) {
        this.classifyName = classifyName;
        return this;
    }

    public String getFactionId() {
        return factionId;
    }

    public QuestionHeadmanBo setFactionId(String factionId) {
        this.factionId = factionId;
        return this;
    }

    public String getFactionName() {
        return factionName;
    }

    public QuestionHeadmanBo setFactionName(String factionName) {
        this.factionName = factionName;
        return this;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public QuestionHeadmanBo setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
        return this;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public QuestionHeadmanBo setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
        return this;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public QuestionHeadmanBo setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public QuestionHeadmanBo setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
