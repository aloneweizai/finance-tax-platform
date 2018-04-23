package com.abc12366.message.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 应用配置文件
 *
 * @author lijun <ljun51@outlook.com>
 * @create 2017-07-27 10:56 AM
 * @since 1.0.0
 */
@Configuration
public class ApplicationConfig {

    // kafka服务器地址
//    @Value("${spring.kafka.bootstrap-servers}")
    public String bootstrap_servers;

    // kafka消费者group-id
//    @Value("${spring.kafka.consumer.group-id}")
    public String consumer_group_id;

    @Value("${message.netease.appKey}")
    private String appKey;
    @Value("${message.netease.appSecret}")
    private String appSecret;

	//电子税局地址
    @Value("${dzsj.soa.url}")
    private String dzsjUrl;

    //电子税局APPID
    @Value("${dzsj.soa.appId}")
    private String appId;

    //电子税局密码
    @Value("${dzsj.soa.secret}")
    private String secret;
    
    //湖南国税地税地址
    @Value("${hnds.url}")
    private String hndsUrl;
    
    @Value("${hnds.key}")
    private String hndsKey;
    
    public String getHndsKey() {
		return hndsKey;
	}

	public void setHndsKey(String hndsKey) {
		this.hndsKey = hndsKey;
	}

	public String getHndsUrl() {
		return hndsUrl;
	}

	public void setHndsUrl(String hndsUrl) {
		this.hndsUrl = hndsUrl;
	}

	public String getDzsjUrl() {
		return dzsjUrl;
	}

	public void setDzsjUrl(String dzsjUrl) {
		this.dzsjUrl = dzsjUrl;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

    public String getBootstrap_servers() {
        return bootstrap_servers;
    }

    public void setBootstrap_servers(String bootstrap_servers) {
        this.bootstrap_servers = bootstrap_servers;
    }

    public String getConsumer_group_id() {
        return consumer_group_id;
    }

    public void setConsumer_group_id(String consumer_group_id) {
        this.consumer_group_id = consumer_group_id;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
    
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
