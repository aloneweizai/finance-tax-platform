package com.abc12366.uc.service;

import com.abc12366.uc.model.Token;
import com.abc12366.uc.model.bo.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

public interface AuthServiceNew {

	UserReturnBO register(RegisterBO registerBO, HttpServletRequest request);

	@SuppressWarnings("rawtypes")
	Map login(LoginBO loginBO, String string);

	boolean resetPasswordByPhone(ResetPasswordBO bo)  throws Exception;

	Boolean updatePassword(PasswordUpdateBO passwordUpdateBO, HttpServletRequest request);

	Map dbAppUserLogin(String userToken);
	
	/**
	 * 获取活动的token列表
	 * @return
	 */
	List<Token> getActiveToken();

}
