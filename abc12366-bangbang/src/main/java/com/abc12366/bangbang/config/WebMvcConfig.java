package com.abc12366.bangbang.config;

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

    @Bean
    public SensitiveWordsInterceptor getSensitiveWordsInterceptor() {
        return new SensitiveWordsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 前置日志、黑名单、后置日志、接口计数拦截
        registry.addInterceptor(logInterceptor())
                .excludePathPatterns("/druid/**");

        // App验证、授权拦截
        registry.addInterceptor(appInterceptor())
                .excludePathPatterns("/")
                .excludePathPatterns("/app/**")
                .excludePathPatterns("/appsetting/**")
                .excludePathPatterns("/api/**")
                .excludePathPatterns("/blacklist/**")
                .excludePathPatterns("/druid/**")
                        //软件用户行为分析
                .excludePathPatterns("/record/statis/auto")
                .excludePathPatterns("/record/statis/company/auto")
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
                        //根据课程评价信息
                .excludePathPatterns("/currEvaluate/selectListBycurrId")
                        //根据课程ID获取讲师信息
                .excludePathPatterns("/lecturer/selectListByCurr")
                        //获取课程分类
                .excludePathPatterns("/classify/selectListsy")
                        //获取课程标签列表
                .excludePathPatterns("/curriculum/selectLabelList")
                        //根据分类查询相关标签
                .excludePathPatterns("/classify/selectClassifyTagList")
                        //获取课程大类小类标签集合
                .excludePathPatterns("/classify/selectClassifyListsy")
                        //获取最新课程
                .excludePathPatterns("/curriculum/selectListNew", "/curriculum/selectListByKnowledgeId")
                        //获取最热课程
                .excludePathPatterns("/curriculum/selectListWatch")
                        //获取推荐课程
                .excludePathPatterns("/curriculum/selectRecommend", "/curriculum/selectRecommendForqt")
                        //获取会员专享课程
                .excludePathPatterns("/curriculum/selectListVIP")
                        //课程浏览量增加
                .excludePathPatterns("/curriculum/updateBrowsesDay/**")
                        //获取活动
                .excludePathPatterns("/event/**")
                        //获取课程详情信息
                .excludePathPatterns("/curriculum/selectCurriculum/**")
                        //查询热门问题
                .excludePathPatterns("/questionbb/selectListByBrowseNum")
                        //查询等你回答的问题
                .excludePathPatterns("/questionbb/selectListWait")
                        //查询已解决的问题
                .excludePathPatterns("/questionbb/selectListAccept")
                        //帮友热议列表查询
                .excludePathPatterns("/questionbb/selectListry", "/questionbb/selectListryForqt")
                        //查询热议标签
                .excludePathPatterns("/questionbb/selectTagList")
                        //查询最新问题
                .excludePathPatterns("/questionbb/selectListNew")
                        //查询推荐问题
                .excludePathPatterns("/questionbb/selectListRecommend")
                        //高悬赏问题
                .excludePathPatterns("/questionbb/selectListByPoints")
                        //问题分类列表查询
                .excludePathPatterns("/queclassify/selectClassifyList")
                        //根据分类查询相关标签
                .excludePathPatterns("/queclassify/selectClassifyTagList")
                        //问题回复列表查询
                .excludePathPatterns("/queAnswer/selectListByQuestionId")
                        //问题回复评论列表查询
                .excludePathPatterns("/queComment/selectList")
                        //最新回答
                .excludePathPatterns("/questionbb/selectListNew")
                        //0回答
                .excludePathPatterns("/questionbb/selectListWait")
                        //查询邦派动态
                .excludePathPatterns("/questionbb/selectQcDtList/**")
                        //查询单个问题
                .excludePathPatterns("/questionbb/selectQuestion/**")
                        //查询单个问题回复信息
                .excludePathPatterns("/queAnswer/selectAnswer/**")
                        //问题更新浏览量
                .excludePathPatterns("/questionbb/updateBrowseNum/**")
                        //优秀邦派列表查询
                .excludePathPatterns("/queFaction/selectListExcellent")
                        //邦派上月排行列表查询
                .excludePathPatterns("/queFaction/selecFactionPhList")
                        //邦派累计列表查询
                .excludePathPatterns("/queFaction/selecFactionljPhList")
                        //邦派任务动态列表查询
                .excludePathPatterns("/queFaction/selectListdt")
                        //邦派任务情况列表查询
                .excludePathPatterns("/queFaction/selectTaskList")
                        //个人主页
                .excludePathPatterns("/questionbb/selectMybangbang/{userId}", "/questionExpert/listByUserId/{userId}",
                        "/queAttention/selectUserList/{attentionUserId}", "/queAttention/selectAttentionUserList/{userId}",
                        "/queAnswer/selectMyAnswerList")
                        //江湖榜 统计
                .excludePathPatterns("/queCount/**")
                        //潜力邦派列表查询
                .excludePathPatterns("/queFaction/selectListPotential")
                        //问大侠列表查询
                .excludePathPatterns("/questionExpert/listDX")
                        //秘籍列表查询(最新)
                .excludePathPatterns("/cheats/selectListNew")
                        //推荐秘籍查询列表
                .excludePathPatterns("/cheats/selectListRecommend")
                        //查询推荐阅读(标题)
                .excludePathPatterns("/cheats/selectListRecommendTitle")
                        //查询你可能感兴趣的文章(标题)
                .excludePathPatterns("/cheats/selectListByTag")
                        //查询热门秘籍
                .excludePathPatterns("/cheats/selectListByBrowseNum")
                        //查询热议标签
                .excludePathPatterns("/cheats/selectTagList")
                        //查询单个秘籍信息
                .excludePathPatterns("/cheats/selectCheats/**")
                        //更新秘籍浏览量
                .excludePathPatterns("/cheats/updateBrowseNum/**")
                        //查询秘籍和话题总数
                .excludePathPatterns("/cheats/selectCheatsAndQuestionCount")
                        //秘籍评论列表查询
                .excludePathPatterns("/cheatsComment/selectList")
                        //帮邦用户列表查询
                .excludePathPatterns("/qcuser/selectList")
                        /*帮邦搜索*/
                .excludePathPatterns("/search/list","/search/cheats/list","/search/question/list")
                //软件用户行为分析
                .excludePathPatterns("/record/statis/auto")
                .excludePathPatterns("/record/statis/company/auto")
                .excludePathPatterns("/hotspot/**")
                .excludePathPatterns("/knowledgeBase/uc/list","/knowledgeBase/hotList","/knowledgeBase/interestedList/**","/knowledgeBase/relatedList/**",
                        "/knowledgeBase/vote/add","/knowledgeBase/view/**","/knowledgeBase/pv/**","/knowledgeCategory/listAll","/knowledgeBase/nearestList",
                        "/KnowledgeTag/listHot/**","/KnowledgeTag/listHot","/knowledgeBase/hotUnClassifyList","/knowledgeBase/wx/hotUnClassifyList");

        // 敏感词拦截
        registry.addInterceptor(getSensitiveWordsInterceptor())
                .excludePathPatterns("/")
                .excludePathPatterns("/app/**")
                .excludePathPatterns("/appsetting/**")
                .excludePathPatterns("/api/**")
                .excludePathPatterns("/blacklist/**")
                .excludePathPatterns("/druid/**")
                .excludePathPatterns("/test");

    }
}
