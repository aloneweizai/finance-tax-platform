package com.abc12366.cms.model.event;

import java.io.Serializable;

/**
 * Created by stuy on 2017/9/14.
 */
public class EventSponsorBbBo implements Serializable {
    private String sponsorid;
    private String sponsorname;

    public String getSponsorid() {
        return sponsorid;
    }

    public void setSponsorid(String sponsorid) {
        this.sponsorid = sponsorid;
    }

    public String getSponsorname() {
        return sponsorname;
    }

    public void setSponsorname(String sponsorname) {
        this.sponsorname = sponsorname;
    }

    public String getSponsorlxr() {
        return sponsorlxr;
    }

    public void setSponsorlxr(String sponsorlxr) {
        this.sponsorlxr = sponsorlxr;
    }

    public String getSponsortel() {
        return sponsortel;
    }

    public void setSponsortel(String sponsortel) {
        this.sponsortel = sponsortel;
    }

    public String getSponsoremail() {
        return sponsoremail;
    }

    public void setSponsoremail(String sponsoremail) {
        this.sponsoremail = sponsoremail;
    }

    public String getSponsorintro() {
        return sponsorintro;
    }

    public void setSponsorintro(String sponsorintro) {
        this.sponsorintro = sponsorintro;
    }

    private String sponsorlxr;
    private String sponsortel;
    private String sponsoremail;
    private String sponsorintro;
}
