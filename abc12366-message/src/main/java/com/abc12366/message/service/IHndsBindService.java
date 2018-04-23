package com.abc12366.message.service;

import java.util.HashMap;


/**
 * 湖南地税登录成功绑定
 * @author zhushuai 2017-11-7
 *
 */
public interface IHndsBindService {

	/**
	 * 自动绑定
	 * @param loginBo
	 */
	int bindHnds(HashMap<String,Object> map);
     
}
