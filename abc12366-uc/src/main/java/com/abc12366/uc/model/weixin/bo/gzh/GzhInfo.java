package com.abc12366.uc.model.weixin.bo.gzh;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

/**
 * 公众号信息
 *
 * @author zhushuai 2017-8-1
 */
public class GzhInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    @NotEmpty
    private String gzhName;//公众号好名
    @NotEmpty
    private String appid;//公众号ID
    @NotEmpty
    private String secret;//密码
    @NotEmpty
    private String tokenStr;//加密串
    @NotEmpty
    private String serverUrl;//回调地址
    
    private String userToken;//usertoken
    private Date userTokenUpdate;//token更新时间
    
    private String jsapi_ticket;//JS tikect
    private Date jsapiTicketUpdate;//JS tikect更新时间
    
    private Date creatDate;//穿件时间
    private Date lastupdate;//更新时间

    
    
    public String getJsapi_ticket() {
		return jsapi_ticket;
	}

	public void setJsapi_ticket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
	}

	public Date getJsapiTicketUpdate() {
		return jsapiTicketUpdate;
	}

	public void setJsapiTicketUpdate(Date jsapiTicketUpdate) {
		this.jsapiTicketUpdate = jsapiTicketUpdate;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGzhName() {
        return gzhName;
    }

    public void setGzhName(String gzhName) {
        this.gzhName = gzhName;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTokenStr() {
        return tokenStr;
    }

    public void setTokenStr(String tokenStr) {
        this.tokenStr = tokenStr;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public Date getUserTokenUpdate() {
		return userTokenUpdate;
	}

	public void setUserTokenUpdate(Date userTokenUpdate) {
		this.userTokenUpdate = userTokenUpdate;
	}


}
