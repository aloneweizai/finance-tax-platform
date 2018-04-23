package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * CMS活动表
 **/
@SuppressWarnings("serial")
public class VoteRotptjBo implements Serializable {

    /**
     * id**varchar(64)
     **/
    private String id;

    /**
     * bz**varchar(64)
     **/
    private String bz;

    /**
     * 统计**varchar(64)
     **/
    private String tj;

    /**
     * 值**int(11)
     **/
    private Integer cnt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getTj() {
        return tj;
    }

    public void setTj(String tj) {
        this.tj = tj;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
}
