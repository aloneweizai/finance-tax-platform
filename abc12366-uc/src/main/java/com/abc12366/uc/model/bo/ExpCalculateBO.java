package com.abc12366.uc.model.bo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 根据用户ID和经验值规则编码计算用户经验值接口入参实体类
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-08-25
 * Time: 10:29
 */
public class ExpCalculateBO {
    //用户ID
    @NotEmpty(message = "用户ID不能为空")
    private String userId;
    //经验值规则ID
    @NotEmpty(message = "经验值编码不能为空")
    private String ruleCode;

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
        return "ExpCalculateBO{" +
                "userId='" + userId + '\'' +
                ", ruleCode='" + ruleCode + '\'' +
                '}';
    }
}
