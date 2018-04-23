package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * CMS活动表
 **/
@SuppressWarnings("serial")
public class AccessLogRolltjBo implements Serializable {

    /**
     * 统计**varchar(64)
     **/
    private String tj;

    /**
     * 值**int(11)
     **/
    private Integer cnt;

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
