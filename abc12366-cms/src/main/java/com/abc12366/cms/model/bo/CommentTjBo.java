package com.abc12366.cms.model.bo;

import java.io.Serializable;


/**
 * CMS评论表
 * add by xieyanmao on 2017-4-25
 **/
@SuppressWarnings("serial")
public class CommentTjBo implements Serializable {

    /**
     * 统计**int(11)
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
