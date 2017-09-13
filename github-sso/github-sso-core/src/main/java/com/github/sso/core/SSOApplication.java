package com.github.sso.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.github.common.config.ConfigConstants;
import com.github.common.config.RedisConfig;
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(ConfigConstants.CLIENT_PKG)
@Import({ RedisConfig.class })
@ComponentScan({ConfigConstants.SERVICE_PKG, ConfigConstants.WEB_PKG})
public class SSOApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(SSOApplication.class, args);
	}

};
