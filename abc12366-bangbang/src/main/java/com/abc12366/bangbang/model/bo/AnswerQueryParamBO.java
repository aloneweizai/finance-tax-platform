package com.abc12366.bangbang.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-12
 * Time: 16:18
 */
public class AnswerQueryParamBO {
    private String userId;
    private String askId;
    private String type;

    public AnswerQueryParamBO() {
    }

    public AnswerQueryParamBO(String userId, String askId, String type) {
        this.userId = userId;
        this.askId = askId;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAskId() {
        return askId;
    }

    public void setAskId(String askId) {
        this.askId = askId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AnswerQueryParamBO{" +
                "userId='" + userId + '\'' +
                ", askId='" + askId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
