package com.abc12366.uc.model.pay;

public class WxDownloadbill {
	    // 公众账号appid
		private String appid;
		// 商户号
		private String mch_id;
		// 设备号
		private String device_info = "WEB";
		// 随机字符串
		private String nonce_str;
		// 签名
		private String sign;
		// 签名类型
		private String sign_type = "MD5";
		
		private String bill_date;
		
		private String bill_type = "ALL";
		
		private String tar_type;

		public String getAppid() {
			return appid;
		}

		public void setAppid(String appid) {
			this.appid = appid;
		}

		public String getMch_id() {
			return mch_id;
		}

		public void setMch_id(String mch_id) {
			this.mch_id = mch_id;
		}

		public String getDevice_info() {
			return device_info;
		}

		public void setDevice_info(String device_info) {
			this.device_info = device_info;
		}

		public String getNonce_str() {
			return nonce_str;
		}

		public void setNonce_str(String nonce_str) {
			this.nonce_str = nonce_str;
		}

		public String getSign() {
			return sign;
		}

		public void setSign(String sign) {
			this.sign = sign;
		}

		public String getSign_type() {
			return sign_type;
		}

		public void setSign_type(String sign_type) {
			this.sign_type = sign_type;
		}

		public String getBill_date() {
			return bill_date;
		}

		public void setBill_date(String bill_date) {
			this.bill_date = bill_date;
		}

		public String getBill_type() {
			return bill_type;
		}

		public void setBill_type(String bill_type) {
			this.bill_type = bill_type;
		}

		public String getTar_type() {
			return tar_type;
		}

		public void setTar_type(String tar_type) {
			this.tar_type = tar_type;
		}
		
		
}
