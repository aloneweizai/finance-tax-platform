package com.abc12366.bangbang.model.question.bo;
import com.abc12366.bangbang.model.question.CheatsTag;

import java.io.Serializable;
import java.util.List;


/**
 * 
 * 秘籍表
 * 
 **/
@SuppressWarnings("serial")
public class CheatsBo implements Serializable {

	/****varchar(64)**/
	private String id;

	/**用户ID**varchar(64)**/
	private String userId;

    /**用户昵称**varchar(64)**/
    private String nickname;

    /**用户图片**/
    private String userPicturePath;

	/**标题**varchar(300)**/
	private String title;

	/**描述**varchar(500)**/
	private String describ;

	/**内容详情**longtext**/
	private String detail;

	/**秘籍状态(0正常，1待审查，2拉黑)**varchar(20)**/
	private String status;

    /**图片**varchar(300)**/
    private String cheatsImage;

	/**创建时间**datetime**/
	private java.util.Date createTime;

	/**修改时间**datetime**/
	private java.util.Date lastUpdate;

	/**秘籍分类**varchar(30)**/
	private String classifyCode;

	/**浏览量**int(11)**/
	private Integer browseNum;

	/**是否推荐**tinyint(1)**/
	private Integer isRecommend;

	/**推荐时间**datetime**/
	private java.util.Date recommendTime;

	/**标签**varchar(1000)**/
	private String tag;

	/**IP**varchar(45)**/
	private String ip;

	/**邦派ID**varchar(64)**/
	private String factionId;

	/**评论数**int(11)**/
	private Integer commentNum;

    /**点赞次数**int(11)**/
    private Integer likeNum;

    /**踩次数**int(11)**/
    private Integer trampleNum;

	/**收藏次数**int(11)**/
	private Integer collectNum;

	/**被举报次数**int(11)**/
	private Integer reportNum;

    /**标签**/
    private List<CheatsTag> tagList;



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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }

    public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setDescrib(String describ){
		this.describ = describ;
	}

	public String getDescrib(){
		return this.describ;
	}

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return this.detail;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setLastUpdate(java.util.Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public java.util.Date getLastUpdate(){
		return this.lastUpdate;
	}

	public void setClassifyCode(String classifyCode){
		this.classifyCode = classifyCode;
	}

	public String getClassifyCode(){
		return this.classifyCode;
	}

	public void setBrowseNum(Integer browseNum){
		this.browseNum = browseNum;
	}

	public Integer getBrowseNum(){
		return this.browseNum;
	}

	public void setIsRecommend(Integer isRecommend){
		this.isRecommend = isRecommend;
	}

	public Integer getIsRecommend(){
		return this.isRecommend;
	}

	public void setRecommendTime(java.util.Date recommendTime){
		this.recommendTime = recommendTime;
	}

	public java.util.Date getRecommendTime(){
		return this.recommendTime;
	}

	public void setTag(String tag){
		this.tag = tag;
	}

	public String getTag(){
		return this.tag;
	}

	public void setIp(String ip){
		this.ip = ip;
	}

	public String getIp(){
		return this.ip;
	}

	public void setFactionId(String factionId){
		this.factionId = factionId;
	}

	public String getFactionId(){
		return this.factionId;
	}

	public void setCommentNum(Integer commentNum){
		this.commentNum = commentNum;
	}

	public Integer getCommentNum(){
		return this.commentNum;
	}

	public void setCollectNum(Integer collectNum){
		this.collectNum = collectNum;
	}

	public Integer getCollectNum(){
		return this.collectNum;
	}

	public void setReportNum(Integer reportNum){
		this.reportNum = reportNum;
	}

	public Integer getReportNum(){
		return this.reportNum;
	}

    public List<CheatsTag> getTagList() {
        return tagList;
    }

    public void setTagList(List<CheatsTag> tagList) {
        this.tagList = tagList;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getTrampleNum() {
        return trampleNum;
    }

    public void setTrampleNum(Integer trampleNum) {
        this.trampleNum = trampleNum;
    }

    public String getCheatsImage() {
        return cheatsImage;
    }

    public void setCheatsImage(String cheatsImage) {
        this.cheatsImage = cheatsImage;
    }
}
