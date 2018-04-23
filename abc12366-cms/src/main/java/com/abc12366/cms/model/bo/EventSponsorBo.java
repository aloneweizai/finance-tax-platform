package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * CMS活动主办方
 **/
@SuppressWarnings("serial")
public class EventSponsorBo implements Serializable {

    /**
     * 活动主办方ID**varchar(64)
     **/
    private String sponsorId;

    /**
     * 活动主办方单位名称**varchar(200)
     **/
    private String sponsorName;

    /**
     * 活动主办方联系人**varchar(50)
     **/
    private String sponsorLxr;

    /**
     * 活动主办方联系电话**varchar(30)
     **/
    private String sponsorTel;

    /**
     * 主办方联系人邮箱**varchar(50)
     **/
    private String sponsorEmail;

    /**
     * 主办方简介**varchar(500)
     **/
    private String sponsorIntro;

    public String getSponsorId() {
        return this.sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getSponsorName() {
        return this.sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getSponsorLxr() {
        return this.sponsorLxr;
    }

    public void setSponsorLxr(String sponsorLxr) {
        this.sponsorLxr = sponsorLxr;
    }

    public String getSponsorTel() {
        return this.sponsorTel;
    }

    public void setSponsorTel(String sponsorTel) {
        this.sponsorTel = sponsorTel;
    }

    public String getSponsorEmail() {
        return this.sponsorEmail;
    }

    public void setSponsorEmail(String sponsorEmail) {
        this.sponsorEmail = sponsorEmail;
    }

    public String getSponsorIntro() {
        return this.sponsorIntro;
    }

    public void setSponsorIntro(String sponsorIntro) {
        this.sponsorIntro = sponsorIntro;
    }

}
