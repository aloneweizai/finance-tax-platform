package com.abc12366.uc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 加载自定义bean文件
 *
 * @author husl on 2017-08-04.
 */
@Configuration
@ImportResource(locations={"classpath:application-bean.xml"})
public class ConfigClass {

	    @Bean
	    @ConfigurationProperties(prefix = "custom.rest.connection")
	    public HttpComponentsClientHttpRequestFactory customHttpRequestFactory() 
	    {
	        return new HttpComponentsClientHttpRequestFactory();
	    }

	    @Bean
	    public RestTemplate customRestTemplate()
	    {
	        return new RestTemplate(customHttpRequestFactory());
	    }
}
