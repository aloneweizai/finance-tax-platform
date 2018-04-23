package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * CMS活动表
 **/
@SuppressWarnings("serial")
public class EventlltjBo implements Serializable {

    /**
     * 浏览时间**varchar(64)
     **/
    private String llsj;

    /**
     * 浏览次数**int(11)
     **/
    private Integer cnt;

    public String getLlsj() {
        return llsj;
    }

    public void setLlsj(String llsj) {
        this.llsj = llsj;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }
}
