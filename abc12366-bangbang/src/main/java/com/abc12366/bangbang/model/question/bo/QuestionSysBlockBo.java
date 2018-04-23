package com.abc12366.bangbang.model.question.bo;

/**
 * @Author liuQi
 * @Date 2017/9/19 11:54
 * 系统过滤帮帮内容（包括问题，回答，评论）
 */
public class QuestionSysBlockBo {

    /**PK**/
    private String id;

    /*用户id*/
    private String userId;

    /*用户name*/
    private String username;

    /* 分类 */
    private String classifyName;

    /**内容**/
    private String content;

    /**内容状态**/
    private String status;

    /**来源id**/
    private String sourceId;

    /**来源类型**/
    private String sourceType;

    /**创建时间**/
    private java.util.Date createTime;

    /**审核用户**/
    private String updateAdmin;

    /**审核时间**/
    private java.util.Date updateTime;

    /**
     * 问题或秘籍ID，作为前端页面链接
     */
    private String linkId;

    public String getContent() {
        return content;
    }

    public QuestionSysBlockBo setContent(String content) {
        this.content = content;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public QuestionSysBlockBo setStatus(String status) {
        this.status = status;
        return this;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setSourceId(String sourceId){
        this.sourceId = sourceId;
    }

    public String getSourceId(){
        return this.sourceId;
    }

    public void setSourceType(String sourceType){
        this.sourceType = sourceType;
    }

    public String getSourceType(){
        return this.sourceType;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime;
    }

    public java.util.Date getCreateTime(){
        return this.createTime;
    }

    public void setUpdateAdmin(String updateAdmin){
        this.updateAdmin = updateAdmin;
    }

    public String getUpdateAdmin(){
        return this.updateAdmin;
    }

    public void setUpdateTime(java.util.Date updateTime){
        this.updateTime = updateTime;
    }

    public java.util.Date getUpdateTime(){
        return this.updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public QuestionSysBlockBo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public QuestionSysBlockBo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public QuestionSysBlockBo setClassifyName(String classifyName) {
        this.classifyName = classifyName;
        return this;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }
}
