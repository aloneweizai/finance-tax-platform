package com.abc12366.uc.service;

import com.abc12366.gateway.model.BodyStatus;
import com.abc12366.uc.model.bo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-03-27 4:03 PM
 * @since 1.0.0
 */
public interface AuthService {

    /**
     * 新平台采用手机号码+登录密码+短信验证码注册，平台自动产生用户ID、用户名（字母UC+时间戳毫秒数）和用户昵称（财税+6位数字），同时自动绑定手机号码。
     * 用户ID作为平台内部字段永久有效且不可更改，平台自动产生的用户名可以允许修改一次且平台内唯一，用户名不能为中文，只能为字母+数字。
     *
     * @param registerBO RegisterBO
     * @param request    HttpServletRequest
     * @return UserReturnBO
     * @see com.abc12366.uc.model.bo.UserReturnBO
     * @see com.abc12366.uc.model.bo.RegisterBO
     */
    UserReturnBO register(RegisterBO registerBO, HttpServletRequest request);

    /**
     * 用户登陆
     *
     * @param loginBO LoginBO
     * @param channel 登陆方式：1-用户名/手机号+密码，2-js用户名/手机号+密码，3-手机号+短信验证码，4-openId登陆
     * @return Map:token,expires_in,用户信息
     */
    Map login(LoginBO loginBO, String channel);

    /**
     * 测试用户登陆,不做RSA加密
     *
     * @param loginBO LoginBO
     * @param channel 登陆方式：1-用户名/手机号+密码，2-js用户名/手机号+密码，3-手机号+短信验证码，4-openId登陆
     * @return Map:token,expires_in,用户信息
     */
    Map testLogin(LoginBO loginBO, String channel);

    boolean isAuthentication(String userToken, HttpServletRequest request);

    /**
     * 校验短信验证码是否正确
     *
     * @param loginVerifyingCodeBO VerifyingCodeBO
     * @param request              HttpServletRequest
     * @return true，验证成功；false，验证失败
     */
    boolean verifyCode(VerifyingCodeBO loginVerifyingCodeBO, HttpServletRequest request);

    /**
     * 用户登出
     *
     * @param token 用户token
     */
    void logout(String token);

    /**
     * 通过验证码登陆失败后的业务处理
     *
     * @param loginBO VerifyingCodeBO
     */
    void loginByVerifyFail(VerifyingCodeBO loginBO);

    /**
     * 用户通过手机号码+验证码的方式身份验证
     *
     * @param phone 手机号
     * @return userToken
     * @throws Exception md5加密异常
     */
    String verifyPhone(String phone) throws Exception;

    /**
     * 通过手机号重置密码，不需要用户登陆
     *
     * @param bo ResetPasswordBO
     * @return true:成功, false:失败
     */
    boolean resetPasswordByPhone(ResetPasswordBO bo) throws Exception;

    /**
     * 登陆之后需要处理的业务
     */
    CompletableFuture<BodyStatus> todoAfterLogin(Map map);

	void computeExp(String userId);

	void insertLoginLog(String userId);
}
