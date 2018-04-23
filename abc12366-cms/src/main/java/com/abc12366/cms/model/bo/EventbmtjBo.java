package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * CMS活动表
 **/
@SuppressWarnings("serial")
public class EventbmtjBo implements Serializable {

    /**
     * 活动名称**varchar(64)
     **/
    private String title;

    /**
     * 活动计划人数**int(11)
     **/
    private Integer peoppleNum;

    /**
     * 实际参与人数**int(11)
     **/
    private Integer cnt1;

    /**
     * 审核通过人数**int(11)
     **/
    private Integer cnt2;

    /**
     * 活动参与比例**varchar(64)
     **/
    private String bl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPeoppleNum() {
        return peoppleNum;
    }

    public void setPeoppleNum(Integer peoppleNum) {
        this.peoppleNum = peoppleNum;
    }

    public Integer getCnt1() {
        return cnt1;
    }

    public void setCnt1(Integer cnt1) {
        this.cnt1 = cnt1;
    }

    public Integer getCnt2() {
        return cnt2;
    }

    public void setCnt2(Integer cnt2) {
        this.cnt2 = cnt2;
    }

    public String getBl() {
        return bl;
    }

    public void setBl(String bl) {
        this.bl = bl;
    }

}
