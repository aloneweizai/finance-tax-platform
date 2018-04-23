package com.abc12366.uc.model.weixin.bo;


import com.abc12366.uc.model.weixin.BaseWxRespon;

/**
 * wx UseToken or ticket
 *
 * @author zhushuai 2017-7-27
 */
public class WxUseToken extends BaseWxRespon {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String access_token;
    private String ticket;
    private Integer expires_in;
    private String openid;
    private String scope;

    public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
