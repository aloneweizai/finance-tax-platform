package com.abc12366.uc.model.job;

import java.io.Serializable;
import java.util.List;

/**
 * 电子申报业务消息
 * @author zhushuai 2017-11-9
 *
 */
public class DzsbJob implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  message;//返回消息
	private String  rescode;//返回码
	private Boolean isExistData; //是否还有数据
	private String ywlx; //业务类型
    private List<DzsbXxInfo> dataList; //数据详情
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRescode() {
		return rescode;
	}
	public void setRescode(String rescode) {
		this.rescode = rescode;
	}
	public Boolean getIsExistData() {
		return isExistData;
	}
	public void setIsExistData(Boolean isExistData) {
		this.isExistData = isExistData;
	}
	public String getYwlx() {
		return ywlx;
	}
	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}
	public List<DzsbXxInfo> getDataList() {
		return dataList;
	}
	public void setDataList(List<DzsbXxInfo> dataList) {
		this.dataList = dataList;
	}
    
    
}
