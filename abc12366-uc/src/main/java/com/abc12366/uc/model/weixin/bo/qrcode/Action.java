package com.abc12366.uc.model.weixin.bo.qrcode;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-08-29 11:46 AM
 * @since 1.0.0
 */
public class Action {

    private String action_name;
    private Info action_info;
    private long expire_seconds;

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public Info getAction_info() {
        return action_info;
    }

    public void setAction_info(Info action_info) {
        this.action_info = action_info;
    }

	public long getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(long expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
}
