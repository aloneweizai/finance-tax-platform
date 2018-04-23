package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-25
 * Time: 10:29
 */
public class PointCalculateBO {
    /**
     * 用户ID
     */
    @NotEmpty
    private String userId;

    /**
     * 积分规则编码
     */
    @NotEmpty
    private String ruleCode;
    
    //2018-02-28
    private Integer points;//奖励/扣除积分值
    
    private String remark;//积分变换规则
    
    public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    @Override
    public String toString() {
        return "PointCalculateBO{" +
                "userId='" + userId + '\'' +
                ", ruleCode='" + ruleCode + '\'' +
                ", points=" + points +
                ", remark='" + remark + '\'' +
                '}';
    }
}
