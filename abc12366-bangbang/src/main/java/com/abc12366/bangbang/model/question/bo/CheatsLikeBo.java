package com.abc12366.bangbang.model.question.bo;
import java.io.Serializable;


/**
 * 
 * 秘籍点赞表
 * 
 **/
@SuppressWarnings("serial")
public class CheatsLikeBo implements Serializable {

	/**点赞ID**varchar(64)**/
	private String likeId;

	/**秘籍或评论ID**varchar(64)**/
	private String id;

	/**点赞对象：1、秘籍，2、评论**int(11)**/
	private Integer likeTarget;

	/**用户ID**varchar(64)**/
	private String userId;

	/**点赞时间**datetime**/
	private java.util.Date likeTime;

	/**秘籍ID**varchar(64)**/
	private String cheatsId;

	/**类型：1、点赞，2、踩**int(11)**/
	private Integer likeType;



	public void setLikeId(String likeId){
		this.likeId = likeId;
	}

	public String getLikeId(){
		return this.likeId;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setLikeTarget(Integer likeTarget){
		this.likeTarget = likeTarget;
	}

	public Integer getLikeTarget(){
		return this.likeTarget;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setLikeTime(java.util.Date likeTime){
		this.likeTime = likeTime;
	}

	public java.util.Date getLikeTime(){
		return this.likeTime;
	}

	public void setCheatsId(String cheatsId){
		this.cheatsId = cheatsId;
	}

	public String getCheatsId(){
		return this.cheatsId;
	}

	public void setLikeType(Integer likeType){
		this.likeType = likeType;
	}

	public Integer getLikeType(){
		return this.likeType;
	}

}
