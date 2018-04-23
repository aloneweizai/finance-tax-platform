package com.abc12366.uc.model.weixin;

import com.abc12366.uc.model.weixin.bo.person.OpenIds;


public class OpenIdRs extends BaseWxRespon {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer total;
    private Integer count;
    private String next_openid;
    private OpenIds data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }

    public OpenIds getData() {
        return data;
    }

    public void setData(OpenIds data) {
        this.data = data;
    }


}
