package com.abc12366.uc.model.weixin.bo.template;

import java.util.Date;

/**
 * 微信模板消息发送日志
 * 
 * @author zhushuai 2017-11-7
 * 
 */
public class TemplateSendLog {
	private String id;// 主键
	private String template_id;// 模板ID
	private String userId;// 用户ID
	private String openid;// 微信ID
	private String templateval;// 消息内容
	private String returncode;// 返回码
	private String returnmsg;// 返回消息
	private Date lastupdate;// 更新时间
	private Date createdate;// 创建时间

	public TemplateSendLog() {

	}

	public TemplateSendLog(String id, String template_id, String userId,
			String openid, String templateval, String returncode,
			String returnmsg, Date lastupdate, Date createdate) {
		this.id = id;
		this.template_id = template_id;
		this.userId = userId;
		this.openid = openid;
		this.templateval = templateval;
		this.returncode = returncode;
		this.returnmsg = returnmsg;
		this.lastupdate = lastupdate;
		this.createdate = createdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getTemplateval() {
		return templateval;
	}

	public void setTemplateval(String templateval) {
		this.templateval = templateval;
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

	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

}
