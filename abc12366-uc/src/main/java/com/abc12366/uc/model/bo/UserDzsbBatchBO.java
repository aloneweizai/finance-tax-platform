package com.abc12366.uc.model.bo;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.abc12366.uc.model.UserDzsb;

/**
 * 用户批量绑定纳税人
 * @author Administrator
 *
 */
public class UserDzsbBatchBO {
	@NotEmpty
	private String username;
	
	@NotEmpty
    private String password;
	
	private List<UserDzsb> userDzsbs;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserDzsb> getUserDzsbs() {
		return userDzsbs;
	}

	public void setUserDzsbs(List<UserDzsb> userDzsbs) {
		this.userDzsbs = userDzsbs;
	}

	
	
	
	
}
