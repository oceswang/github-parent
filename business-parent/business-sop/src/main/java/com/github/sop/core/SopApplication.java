package com.github.sop.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.github.common.config.ConfigConstants;
import com.github.common.config.JPAConfigutation;
import com.github.common.config.SwaggerConfiguration;
import com.github.common.spring.ApplicationContextHolder;
import com.github.sso.api.config.OAuthConfig;

@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient
@EnableFeignClients(ConfigConstants.CLIENT_PKG)
@Import({ JPAConfigutation.class, SwaggerConfiguration.class, OAuthConfig.class })
@ComponentScan({ ConfigConstants.SERVICE_PKG, ConfigConstants.WEB_PKG })
public class SopApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(SopApplication.class, args);
	}

	@Bean
	public ApplicationContextHolder applicationContextHolder()
	{
		return ApplicationContextHolder.getInstance();
	}
}
