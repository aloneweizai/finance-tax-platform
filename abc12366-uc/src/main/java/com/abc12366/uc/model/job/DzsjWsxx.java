package com.abc12366.uc.model.job;

import java.util.List;

/**
 * 电子税局文书信息提醒返回
 * @author zhushuai 2017-11-10
 *
 */
public class DzsjWsxx {
	
    private String msg; //返回结果
    private String code; //返回码
    private Integer sl; //剩余数量
    
    private List<WsxxInfo> list;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getSl() {
		return sl;
	}
	public void setSl(Integer sl) {
		this.sl = sl;
	}
	public List<WsxxInfo> getList() {
		return list;
	}
	public void setList(List<WsxxInfo> list) {
		this.list = list;
	}
    
    
}
