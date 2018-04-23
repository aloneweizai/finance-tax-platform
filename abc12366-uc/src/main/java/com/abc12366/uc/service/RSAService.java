package com.abc12366.uc.service;

/**
 * User: liuguiyao<435720953@qq.com>
 * Date: 2017-09-11
 * Time: 16:54
 */
public interface RSAService {

    /**
     * RSA解密方法,前台java加密用此方法解密
     * @param str
     * @return
     */
    String decode(String str);

    /**
     * RSA解密方法,前台js加密用此方法解密
     * @param str
     * @return
     */
    String decodeStringFromJs(String str);

	String decodenNew(String password);
	
	public String decodeStringFromJsNew(String str);
}
