package com.abc12366.bangbang.model.question.bo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 帮邦用户表
 * 
 **/
@SuppressWarnings("serial")
public class QcBangUserBo implements Serializable {

	/**ID**varchar(64)**/
	private String id;

	/**用户ID**varchar(64)**/
	private String userId;

    /**用户昵称**/
    private String nickname;

    /**用户图片地址**/
    private String userPicturePath;

    /**擅长领域**/
    private String tags;

    /**提问次数**int(11)**/
    private Integer questionNum;

    /**点赞次数**int(11)**/
    private Integer likeNum;

    /**回复次数**int(11)**/
    private Integer answerNum;

    /**评论次数**int(11)**/
    private Integer commentNum;

    /**采纳次数**int(11)**/
    private Integer acceptNum;

    /**收藏数**int(11)**/
    private Integer collectNum;

    /**我的关注数量**int(11)**/
    private Integer attentionNum;

    /**我的粉丝数量**int(11)**/
    private Integer fansNum;

    /**勋章数**int(11)**/
    private Integer medalNum;

    /**创建时间**datetime**/
    private Date createTime;

    /**修改时间**datetime**/
    private Date lastUpdate;

    private List<QcTitleBo> qcTitleBoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getAcceptNum() {
        return acceptNum;
    }

    public void setAcceptNum(Integer acceptNum) {
        this.acceptNum = acceptNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getAttentionNum() {
        return attentionNum;
    }

    public void setAttentionNum(Integer attentionNum) {
        this.attentionNum = attentionNum;
    }

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    public Integer getMedalNum() {
        return medalNum;
    }

    public void setMedalNum(Integer medalNum) {
        this.medalNum = medalNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<QcTitleBo> getQcTitleBoList() {
        return qcTitleBoList;
    }

    public void setQcTitleBoList(List<QcTitleBo> qcTitleBoList) {
        this.qcTitleBoList = qcTitleBoList;
    }
}
