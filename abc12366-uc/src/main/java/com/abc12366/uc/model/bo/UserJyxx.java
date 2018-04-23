package com.abc12366.uc.model.bo;

/**
 * 用户建议信息
 * 
 * @author Administrator
 *
 */
public class UserJyxx {
	private String id;
	private String username;
	private String phone;
	private String salt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
