package com.abc12366.bangbang.model.question.bo;

/**
 * @Author liuQi
 * @Date 2017/11/1 11:06
 */
public class AllocationPointAwardBO {

    private String factionId;

    private String userId;

    private Integer point;

    public String getFactionId() {
        return factionId;
    }

    public AllocationPointAwardBO setFactionId(String factionId) {
        this.factionId = factionId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
