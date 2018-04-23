package com.abc12366.bangbang.model.question.bo;

/**
 * @Author liuQi
 * @Date 2017/10/28 14:31
 */
public class QuestionFactionRewardSettingParamBo {

    /**/
    private String factionName;

    /**/
    private Boolean status;

    /**/
    private String date;

    /**/
    private Integer factionNumOfMonth;

    public String getFactionName() {
        return factionName;
    }

    public QuestionFactionRewardSettingParamBo setFactionName(String factionName) {
        this.factionName = factionName;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public QuestionFactionRewardSettingParamBo setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public String getDate() {
        return date;
    }

    public QuestionFactionRewardSettingParamBo setDate(String date) {
        this.date = date;
        return this;
    }


    public Integer getFactionNumOfMonth() {
        return factionNumOfMonth;
    }

    public QuestionFactionRewardSettingParamBo setFactionNumOfMonth(Integer factionNumOfMonth) {
        this.factionNumOfMonth = factionNumOfMonth;
        return this;
    }
}
