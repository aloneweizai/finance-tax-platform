package com.abc12366.uc.util;

import com.abc12366.gateway.exception.ServiceException;
import com.abc12366.gateway.util.Utils;

/**
 * Admin: liuguiyao<435720953@qq.com>
 * Date: 2017-07-19
 * Time: 10:51
 */
public class PasswordUtils {
    public static String encodePassword(String password, String salt) {
        //密码加密
        String encodePassword;
        try {
            //密码生产规则：前台传密码md5之后的值，后台用该值加上salt再md5 ，salt是随机生成的六位整数
            password = Utils.md5(password);
            encodePassword = Utils.md5(password + salt);
        } catch (Exception e) {
            throw new ServiceException(4106);
        }
        return encodePassword;
    }

    public static String encodeUpdatePassword(String password, String salt) {
        //密码加密
        String encodePassword;
        try {
            //密码生产规则：前台传密码md5之后的值，后台用该值加上salt再md5 ，salt是随机生成的六位整数
            encodePassword = Utils.md5(password + salt);
        } catch (Exception e) {
            throw new ServiceException(4106);
        }
        return encodePassword;
    }
}
