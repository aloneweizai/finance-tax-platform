package com.abc12366.bangbang.model.question;

/**
 * Created by stuy on 2017/11/9.
 */
public class CheatsSearchBo {

//    秘籍编号
    private String cheatsId;

//    秘籍标题
    private String title;

//    秘籍简短内容
    private String describ;

//    秘籍用户编号
    private String userId;

//    评论数
    private Integer commentNum;

//    收藏数
    private Integer collectNum;

//    点赞数
    private Integer likeNum;

//    踩数
    private Integer trampleNum;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String userPicturePath;


    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCheatsId() {
        return cheatsId;
    }

    public void setCheatsId(String cheatsId) {
        this.cheatsId = cheatsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
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
}
