package com.abc12366.bangbang.model.bo;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-06-12
 * Time: 16:15
 */
public class AnswersQueryParamBO {
    private String userId;
    private String askId;
    private String type;
    private String status;

    public AnswersQueryParamBO() {
    }

    public AnswersQueryParamBO(String userId, String askId, String type, String status) {
        this.userId = userId;
        this.askId = askId;
        this.type = type;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AnswersQueryParamBO{" +
                "userId='" + userId + '\'' +
                ", askId='" + askId + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
