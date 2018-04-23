package com.abc12366.bangbang.model.bo;

/**
 * @Author liuqi
 * @Date 2017/8/7 18:03
 */
public class KnowledgeVoteLogBO {


    /**知识库投票记录表**/
    private String id;

    /**投票用户ID**/
    private String userId;

    /**投票用户名称**/
    private String userName;

    /**知识ID**/
    private String knowledgeId;

    /**知识主题**/
    private String knowledgeSubject;

    /**是否有用**/
    private Boolean isUseFull;

    /**来源IP**/
    private String sourceIP;

    /**创建时间**/
    private java.util.Date createTime;

    /** 该条投票的回复信息 **/
    private String replyMsg;



    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return this.userId;
    }

    public void setKnowledgeId(String knowledgeId){
        this.knowledgeId = knowledgeId;
    }

    public String getKnowledgeId(){
        return this.knowledgeId;
    }

    public Boolean getIsUseFull() {
        return isUseFull;
    }

    public void setIsUseFull(Boolean isUseFull) {
        this.isUseFull = isUseFull;
    }

    public void setSourceIP(String sourceIP){
        this.sourceIP = sourceIP;
    }

    public String getSourceIP(){
        return this.sourceIP;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime;
    }

    public java.util.Date getCreateTime(){
        return this.createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKnowledgeSubject() {
        return knowledgeSubject;
    }

    public void setKnowledgeSubject(String knowledgeSubject) {
        this.knowledgeSubject = knowledgeSubject;
    }

    public String getReplyMsg() {
        return replyMsg;
    }

    public KnowledgeVoteLogBO setReplyMsg(String replyMsg) {
        this.replyMsg = replyMsg;
        return this;
    }
}
