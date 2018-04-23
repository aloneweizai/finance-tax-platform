package com.abc12366.uc.model.weixin.bo.template;

import java.util.Date;

/**
 * 微信模板消息发送日志查询类
 * 
 * @author zhushuai 2017-11-7
 * 
 */
public class QTemplateSendLog {
    private String id;//日志ID
    private String returncode; //发送结果吗
    private String returnmsg;//发送结果
    private String templateval;//模板消息
    private String username; //用户名
    private String nickname; //昵称
    private Date createdate; //发送时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReturncode() {
		return returncode;
	}
	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}
	public String getReturnmsg() {
		return returnmsg;
	}
	public void setReturnmsg(String returnmsg) {
		this.returnmsg = returnmsg;
	}
	public String getTemplateval() {
		return templateval;
	}
	public void setTemplateval(String templateval) {
		this.templateval = templateval;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
    
    
}
