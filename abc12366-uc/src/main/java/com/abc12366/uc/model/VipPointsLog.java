package com.abc12366.uc.model;

/**
 * 积分转换日志
 * @author zhushuai 2017-11-20
 *
 */
public class VipPointsLog {
	 
     private String sendId;//转让人
     private String reciveId;//接收人
     private Integer points;//装让积分
     
     private String signature;//签名

	 public String getSendId() {
		return sendId;
	 }

	 public void setSendId(String sendId) {
		this.sendId = sendId;
	 }

	
	public String getReciveId() {
		return reciveId;
	}

	public void setReciveId(String reciveId) {
		this.reciveId = reciveId;
	}


	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
     
     
}
