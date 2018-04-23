package com.abc12366.bangbang.model.question.bo;
import com.abc12366.bangbang.model.question.QuestionFactionClassify;
import com.abc12366.bangbang.model.question.QuestionFactionTag;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 邦派表
 * 
 **/
@SuppressWarnings("serial")
public class QuestionFactionListBo implements Serializable {

	/**邦派ID**varchar(64)**/
	private String factionId;

	/**邦派名称**varchar(200)**/
	private String factionName;

	/**邦派图片**varchar(300)**/
	private String factionImg;

	/**邦派分类**varchar(500)**/
	private String classifyCode;

	/**邦派标签**varchar(500)**/
	private String tag;

	/**入邦最低等级**varchar(64)**/
	private String minGrade;

	/**简介**varchar(1000)**/
	private String intro;

	/**是否自动通过**tinyint(4)**/
	private Integer auto;

	/**邀请链接**varchar(300)**/
	private String inviteLink;

	/**创建时间**datetime**/
	private java.util.Date createTime;

	/**修改时间**datetime**/
	private java.util.Date updateTime;

	/**创建者**varchar(64)**/
	private String userId;

    /**帮主昵称**/
    private String nickname;

    /**副帮主昵称**/
    private String nickname2;

	/**状态**int(2)**/
	private Integer state;

	/**公告**varchar(500)**/
	private String announcement;

	/**最大人数限制**int(10)**/
	private Integer peopleLimit;

	/**邦派等级**varchar(64)**/
	private String factionGrade;

    private List<QuestionFactionTag> tagList;

    private List<QuestionFactionClassify> classifyList;

    /**成员数****/
    private Integer peopleNum;

    /**邦派荣誉值****/
    private String honor;

    /**提问数****/
    private Integer questionNum;

    /**回答数****/
    private Integer answerNum;

    /**讨论数****/
    private Integer discussNum;

    /**采纳回答数****/
    private Integer adoptNum;

    public String getFactionId() {
        return factionId;
    }

    public void setFactionId(String factionId) {
        this.factionId = factionId;
    }

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }

    public String getFactionImg() {
        return factionImg;
    }

    public void setFactionImg(String factionImg) {
        this.factionImg = factionImg;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMinGrade() {
        return minGrade;
    }

    public void setMinGrade(String minGrade) {
        this.minGrade = minGrade;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getAuto() {
        return auto;
    }

    public void setAuto(Integer auto) {
        this.auto = auto;
    }

    public String getInviteLink() {
        return inviteLink;
    }

    public void setInviteLink(String inviteLink) {
        this.inviteLink = inviteLink;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public Integer getPeopleLimit() {
        return peopleLimit;
    }

    public void setPeopleLimit(Integer peopleLimit) {
        this.peopleLimit = peopleLimit;
    }

    public String getFactionGrade() {
        return factionGrade;
    }

    public void setFactionGrade(String factionGrade) {
        this.factionGrade = factionGrade;
    }

    public List<QuestionFactionTag> getTagList() {
        return tagList;
    }

    public void setTagList(List<QuestionFactionTag> tagList) {
        this.tagList = tagList;
    }

    public List<QuestionFactionClassify> getClassifyList() {
        return classifyList;
    }

    public void setClassifyList(List<QuestionFactionClassify> classifyList) {
        this.classifyList = classifyList;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname2() {
        return nickname2;
    }

    public void setNickname2(String nickname2) {
        this.nickname2 = nickname2;
    }
}
