package com.abc12366.uc.model.weixin.bo.template;

import org.apache.commons.lang3.StringUtils;

import com.abc12366.gateway.component.SpringCtxHolder;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 微信模板消息
 * 
 * @author zhushuai 2017-11-6
 *
 */
public class Template implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String template_id;// 模板ID
	private String title;// 模板标题
	private String primary_industry; // 模板所属行业的一级行业
	private String deputy_industry; // 模板所属行业的二级行业
	private String content;// 模板内容
	private String example;// 示例

	private Date lastupdate; // 同步时间

	/**
	 * 模板消息转换
	 * 
	 * @param dataList
	 * @return
	 */
	public String toSendJson(Map<String, String> dataList) {
		String[] keys = StringUtils.substringsBetween(this.content, "{{", ".DATA}}");
		StringBuffer json = new StringBuffer();
		json.append("{");
		json.append("\"touser\":\"" + dataList.get("openId") + "\",");
		json.append("\"template_id\":\"" + this.template_id + "\",");
		json.append("\"url\":\"" + (StringUtils.isNotEmpty(dataList.get("url")) ? dataList.get("url")
				: SpringCtxHolder.getProperty("mbxx.cszj.url")) + "\",");
		json.append("\"data\": {");
		int i = 0;
		for (String key : keys) {
			if (i > 0) {
				json.append(",");
			}
			json.append("\"" + key + "\":{");
			json.append("\"value\":\"" + (StringUtils.isEmpty(dataList.get(key)) ? "" : dataList.get(key)) + "\",");
			json.append("\"color\":\""
					+ (StringUtils.isEmpty(dataList.get(key + "Color")) ? "#0000FF" : dataList.get(key + "Color"))
					+ "\"");
			json.append("}");
			i++;
		}

		json.append("}}");
		return json.toString();
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrimary_industry() {
		return primary_industry;
	}

	public void setPrimary_industry(String primary_industry) {
		this.primary_industry = primary_industry;
	}

	public String getDeputy_industry() {
		return deputy_industry;
	}

	public void setDeputy_industry(String deputy_industry) {
		this.deputy_industry = deputy_industry;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

}
