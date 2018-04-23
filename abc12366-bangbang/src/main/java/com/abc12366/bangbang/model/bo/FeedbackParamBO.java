package com.abc12366.bangbang.model.bo;

/**
 * @Author liuqi
 * @Date 2017/8/7 13:45
 */
public class FeedbackParamBO {

    /* 来源类型 */
    private String sourceType;

    /* 反馈类型 */
    private String feedbackType;

    /* 是否回复 */
    private Boolean isReply;


    public FeedbackParamBO(String sourceType, String feedbackType, Boolean isReply) {
        this.sourceType = sourceType;
        this.feedbackType = feedbackType;
        this.isReply = isReply;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public Boolean getIsReply() {
        return isReply;
    }

    public FeedbackParamBO setIsReply(Boolean isReply) {
        this.isReply = isReply;
        return this;
    }
}
