package com.github.project.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
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
import com.github.event.config.EventConfiguration;

@SpringBootApplication(exclude = { RabbitAutoConfiguration.class })
@EnableHystrix
@EnableDiscoveryClient
@EnableFeignClients(ConfigConstants.CLIENT_PKG)
@Import({ JPAConfigutation.class ,SwaggerConfiguration.class, EventConfiguration.class})
@ComponentScan({ ConfigConstants.SERVICE_PKG, ConfigConstants.WEB_PKG })
public class ProjectApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	public ApplicationContextHolder applicationContextHolder()
	{
		return ApplicationContextHolder.getInstance();
	}

}
