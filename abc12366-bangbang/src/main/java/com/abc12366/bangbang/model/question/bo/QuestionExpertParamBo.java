package com.abc12366.bangbang.model.question.bo;

/**
 * @Author liuQi
 * @Date 2017/10/23 14:46
 */
public class QuestionExpertParamBo {

    /*用户名称*/
    private String username;

    /*真实名称*/
    private String realName;

    /*联系电话*/
    private String phone;

    /*专家类型  财税大侠，税务大侠*/
    private String type;

    /*状态*/
    private String status;

    public String getUsername() {
        return username;
    }

    public QuestionExpertParamBo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public QuestionExpertParamBo setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public QuestionExpertParamBo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getType() {
        return type;
    }

    public QuestionExpertParamBo setType(String type) {
        this.type = type;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public QuestionExpertParamBo setStatus(String status) {
        this.status = status;
        return this;
    }
}
