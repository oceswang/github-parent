package com.github.oauth.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.github.common.config.ConfigConstants;
import com.github.common.config.JPAConfigutation;
import com.github.common.config.RedisConfig;
import com.github.common.config.SwaggerConfiguration;
@SpringBootApplication
@EnableDiscoveryClient
@Import({ JPAConfigutation.class, SwaggerConfiguration.class, RedisConfig.class })
@ComponentScan({ConfigConstants.SERVICE_PKG, ConfigConstants.WEB_PKG})
public class OAuthApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(OAuthApplication.class, args);
	}

};
