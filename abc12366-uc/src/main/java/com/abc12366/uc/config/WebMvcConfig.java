package com.abc12366.uc.config;

import com.abc12366.gateway.component.AppInterceptor;
import com.abc12366.gateway.component.LogInterceptor;
import com.abc12366.gateway.component.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @author lijun <ljun51@outlook.com>
 * @create 2017-04-05 10:24 AM
 * @since 1.0.0
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
		System.out.println("Locale: " + Locale.getDefault());
		return slr;
	}

	@Bean
	public LogInterceptor logInterceptor() {
		return new LogInterceptor();
	}

	@Bean
	public AppInterceptor appInterceptor() {
		return new AppInterceptor();
	}

	@Bean
	public TokenInterceptor tokenInterceptor() {
		return new TokenInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 前置日志、黑名单、后置日志、接口计数拦截
		registry.addInterceptor(logInterceptor()).excludePathPatterns("/druid/**")
				// 第三方交易回调地址
				.excludePathPatterns("/payreturn/**")
				// 微信服务回调地址
				.excludePathPatterns("/wechatserver/*")
				// 微信支付回调
				.excludePathPatterns("/payreturn/wxpay","/payreturn/wxrefund");

		// App验证、授权拦截
		registry.addInterceptor(appInterceptor()).excludePathPatterns("/").excludePathPatterns("/app/**")
				.excludePathPatterns("/appsetting/**").excludePathPatterns("/api/**")
				.excludePathPatterns("/blacklist/**").excludePathPatterns("/druid/**").excludePathPatterns("/test")
				// 第三方交易回调地址
				.excludePathPatterns("/payreturn/**")
				// 微信回调地址
				.excludePathPatterns("/wechatserver/init/**")
				// 操作员登录、登出、token验证自动刷新
				.excludePathPatterns("/admin/token/**")
				// 微信服务回调地址
				.excludePathPatterns("/wechatserver/*")
				// 微信支付回调
				.excludePathPatterns("/payreturn/wxpay","/payreturn/wxrefund");

		// 前台用户访问拦截器迁移到网关后的
		registry.addInterceptor(tokenInterceptor()).excludePathPatterns("/").excludePathPatterns("/app/**")
				.excludePathPatterns("/goods/user/**").excludePathPatterns("/dict/**")
				.excludePathPatterns("/appsetting/**").excludePathPatterns("/api/**")
				.excludePathPatterns("/blacklist/**").excludePathPatterns("/druid/**").excludePathPatterns("/test")
				// 用户登录、验证码登录、登出、token刷新、用户注册、测试、token验证自动刷新、验证码
				.excludePathPatterns("/login", "/login/js", "/verifylogin", "/logout/**", "/refresh", "/register",
						"/user/token/**", "/user/u/**", "/v2/login", "/v2/login/js", "/v2/verifylogin", "/v2/register",
						"/v2/dbappuserlogin", "/test/login")
				// 用户通过手机号重置密码
				.excludePathPatterns("/verifyphone", "/resetpassword", "/v2/resetpassword")
				// 根据用户ID查看用户简单信息：用户ID，用户昵称，用户头像，擅长领域
				.excludePathPatterns("/user/notoken/simple/{userId}")
				// 操作员登录、登出、token验证自动刷新
				.excludePathPatterns("/admin/login", "/admin/logout/**", "/admin/token/**")
				// 第三方交易毁回调地址
				.excludePathPatterns("/payreturn/**")
				// 微信服务回调地址
				.excludePathPatterns("/wechatserver/*", "/wxTemplate/send/*")
				// 用户等级接口地址
				.excludePathPatterns("/uvip/level/**")
				// 用户签到排行榜
				.excludePathPatterns("/check/rank")
				// 计算用户经验值接口
				.excludePathPatterns("/experience/compute")
				// 根据省市区编号查询名称
				.excludePathPatterns("/provinceorcityorarea")
				// JS获取微信信息
				.excludePathPatterns("/wxgzh/getuserid/**", "/wxgzh/getuserinfo/**", "/wxgzh/getwxJsConfig",
						"/user/wx/**", "/user/wx/openid/**", "/wx/redpack", "/wx/activity/redpack",
						"/wxuser/getOneFromWx/**")
				// rsa密码
				.excludePathPatterns("/rsa/public", "/rsa/private", "/v2/rsa/public", "/v2/rsa/private", "/rsa/login")
				// 微信回调地址
				.excludePathPatterns("/wechatserver/init/**")
				// 好会计
				.excludePathPatterns("/userhkj/token")
				// 用户验证码登录发送验证码短信
				.excludePathPatterns("/user/phonelogin/code")
				// 根据微信活动生成红包口令, 查询微信红包活动
				.excludePathPatterns("/wx/redpack/{activityId}", "/wx/activity/redpack")
				// 批量用户奖励积分接口
				.excludePathPatterns("/points/batch/award")
				// 长链接转短链接接口
				.excludePathPatterns("/long2short", "/tinyurl")
				// 办税日历查询
				.excludePathPatterns("/user/bsrl", "/user/u/jyxx/{usernameOrPhone}")
				// 汇算清缴注册
				.excludePathPatterns("/v2/batch/bind/dzsb")
				// 微信支付回调
				.excludePathPatterns("/payreturn/wxpay","/payreturn/wxrefund");
	}
}
