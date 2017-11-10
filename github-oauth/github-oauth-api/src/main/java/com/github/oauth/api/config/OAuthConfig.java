package com.github.oauth.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class OAuthConfig extends WebMvcConfigurerAdapter
{

	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(loginIntecetpor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
	
	@Bean
	public HandlerInterceptor loginIntecetpor()
	{
		return new LoginInterceptor();
	}
}
