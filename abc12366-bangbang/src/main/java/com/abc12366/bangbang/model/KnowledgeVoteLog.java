package com.abc12366.bangbang.model;

/**
 * 知识库投票记录表
 **/
public class KnowledgeVoteLog  {

	/**知识库投票记录表**/
	private String id;

	/**投票用户ID**/
	private String userId;

	/**知识ID**/
	private String knowledgeId;

	/**是否有用**/
	private Boolean isUseFull;

	/**来源IP**/
	private String sourceIP;

	/**创建时间**/
	private java.util.Date createTime;



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
}
