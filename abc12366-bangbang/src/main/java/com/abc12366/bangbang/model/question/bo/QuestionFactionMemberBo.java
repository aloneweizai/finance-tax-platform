package com.abc12366.bangbang.model.question.bo;
import java.io.Serializable;


/**
 * 
 * 邦派成员表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionFactionMemberBo implements Serializable {

	/**邦派成员ID**varchar(64)**/
	private String memberId;

	/**邦派ID**varchar(64)**/
	private String factionId;

	/**用户ID**varchar(64)**/
	private String userId;

	/**用户名**varchar(32)**/
	private String username;

	/**昵称**varchar(32)**/
	private String nickname;

    /**用户图片**/
    private String userPicturePath;

	/**状态**tinyint(1)**/
	private Integer status;

	/**创建时间**datetime**/
	private java.util.Date createTime;

	/**修改时间**datetime**/
	private java.util.Date lastUpdate;

    /**职务**varchar(64)**/
    private String duty;

    /**等级**varchar(64)**/
    private String memberGrade;

    /**回答次数**int(11)**/
    private Integer answerNum;

    /**是否已关注**int(11)**/
    private Integer isAttention;

    /**贡献值****/
    private String honor;

    /**成员讨论数****/
    private Integer discussNum;

    /**成员采纳回答数****/
    private Integer adoptNum;



	public void setMemberId(String memberId){
		this.memberId = memberId;
	}

	public String getMemberId(){
		return this.memberId;
	}

	public void setFactionId(String factionId){
		this.factionId = factionId;
	}

	public String getFactionId(){
		return this.factionId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return this.userId;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return this.username;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public String getNickname(){
		return this.nickname;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
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

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public Integer getDiscussNum() {
        return discussNum;
    }

    public void setDiscussNum(Integer discussNum) {
        this.discussNum = discussNum;
    }

    public Integer getAdoptNum() {
        return adoptNum;
    }

    public void setAdoptNum(Integer adoptNum) {
        this.adoptNum = adoptNum;
    }

    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }

    public Integer getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(Integer isAttention) {
        this.isAttention = isAttention;
    }

    public String getMemberGrade() {
        return memberGrade;
    }

    public void setMemberGrade(String memberGrade) {
        this.memberGrade = memberGrade;
    }
}
