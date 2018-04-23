package com.abc12366.cms.config;

import com.abc12366.gateway.component.AppInterceptor;
import com.abc12366.gateway.component.LogInterceptor;
import com.abc12366.gateway.component.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
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
    public RestTemplate restTemplate() {
        return new RestTemplate();
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
        registry.addInterceptor(logInterceptor())
                .excludePathPatterns("/druid/**");

        // App验证、授权拦截(Access-Token)
        registry.addInterceptor(appInterceptor())
                .excludePathPatterns("/")
                .excludePathPatterns("/app/**")
                .excludePathPatterns("/appsetting/**")
                .excludePathPatterns("/api/**")
                .excludePathPatterns("/blacklist/**")
                .excludePathPatterns("/druid/**")
                .excludePathPatterns("/test");

        //前台用户访问拦截器迁移到网关后的
        registry.addInterceptor(tokenInterceptor())
                .excludePathPatterns("/")
                .excludePathPatterns("/app/**")
                .excludePathPatterns("/appsetting/**")
                .excludePathPatterns("/api/**")
                .excludePathPatterns("/blacklist/**")
                .excludePathPatterns("/druid/**")
                .excludePathPatterns("/test")
                        //定时任务查询不需验证
                .excludePathPatterns("/task/list")
                        //修改内容浏览量
                .excludePathPatterns("/content/updateViewsDay/**")
                        //获取标签
                .excludePathPatterns("/content/selectContentType")
                        //根据内容标签名称、栏目名称查询内容列表信息
                .excludePathPatterns("/content/selectListByTagName")
                        //根据内容标签查询内容列表信息
                .excludePathPatterns("/content/selectListByContentType")
                        //根据内容ID查询内容信息
                .excludePathPatterns("/content/selectContent")
                        //搜索资讯
                .excludePathPatterns("/content/selectListSearch")
                        //获取财税资讯网
                .excludePathPatterns("/content/selectListcszxw", "/content/selectListcszxwForqt")
                        //根据栏目id获取财税资讯列表
                .excludePathPatterns("/content/selectListByChannelId")
                        //获取最新的活动信息
                .excludePathPatterns("/event/topone/**")
                        //获取活动列表信息
                .excludePathPatterns("/event/selectEventList")
                        //根据访问量获取文章信息
                .excludePathPatterns("/content/selectListByviews")
                        //获取推荐课程
                .excludePathPatterns("/content/selectRecommend", "/content/selectRecommendForqt")
                        //帮帮活动查询接口
                .excludePathPatterns("/bangbang/event/saveeventrecord","/bangbang/event/singleevent","/bangbang/event/singleeventlist","/bangbang/event/details/{eventid}")
                .excludePathPatterns("/bangbang/event/details/**")
                        // 财税专家客户端广告页、通知公告
                .excludePathPatterns("/adpages", "adpages/**", "/notices", "/notices/**", "/noticesForqt")
                        //发现之旅财税资讯列表
                .excludePathPatterns("/channel/list")
                        //问卷
                .excludePathPatterns("/question/**")
                .excludePathPatterns("/subjects/**")
                .excludePathPatterns("/accessLog/**")
                .excludePathPatterns("/answer/batch")
                .excludePathPatterns("/answer/selectdtcnt")
                        //多个结果投票
                .excludePathPatterns("/vote/**")
                .excludePathPatterns("/vote/result/**")
                .excludePathPatterns("/vote/nloginSelecttj");
    }
}
