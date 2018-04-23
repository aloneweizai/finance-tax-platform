package com.abc12366.bangbang.model.question;

import java.util.List;

/**
 * Created by stuy on 2017/11/9.
 */
public class SearchBo {

    private List<QuestionSearchBo> questionSearchBoList;

    private List<CheatsSearchBo> cheatsSearchBoList;

    private long questionTotal;

    private long cheatsTotal;

    public List<QuestionSearchBo> getQuestionSearchBoList() {
        return questionSearchBoList;
    }

    public void setQuestionSearchBoList(List<QuestionSearchBo> questionSearchBoList) {
        this.questionSearchBoList = questionSearchBoList;
    }

    public List<CheatsSearchBo> getCheatsSearchBoList() {
        return cheatsSearchBoList;
    }

    public void setCheatsSearchBoList(List<CheatsSearchBo> cheatsSearchBoList) {
        this.cheatsSearchBoList = cheatsSearchBoList;
    }

    public long getQuestionTotal() {
        return questionTotal;
    }

    public void setQuestionTotal(long questionTotal) {
        this.questionTotal = questionTotal;
    }

    public long getCheatsTotal() {
        return cheatsTotal;
    }

    public void setCheatsTotal(long cheatsTotal) {
        this.cheatsTotal = cheatsTotal;
    }
}
