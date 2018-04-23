package com.abc12366.bangbang.model.question.bo;

/**
 * @Author liuQi
 * @Date 2017/10/26 14:49
 */
public enum QuestionTipOffStatus {

    auditing("待审核"),
    approved("审核通过"),
    refuse("审核拒绝");

    private String description;

    QuestionTipOffStatus(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
