package com.github.oauth.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.oauth.core.utils.SSOConstants;

@Service
public class AuthService
{
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void addCode(String code, String openId)
	{
		redisTemplate.boundValueOps(SSOConstants.CACHE_CODE_PREFIX + code).set(openId);
	}

	public boolean checkCode(String code)
	{
		return redisTemplate.hasKey(SSOConstants.CACHE_CODE_PREFIX + code);
	}

	public String getOpenIdByCode(String code)
	{
		return redisTemplate.boundValueOps(SSOConstants.CACHE_CODE_PREFIX + code).get();
	}

	public void addToken(String token, String code)
	{
		String openId = getOpenIdByCode(code);
		redisTemplate.boundValueOps(SSOConstants.CACHE_TOKEN_PREFIX + token).set(openId);
	}

	public boolean checkToken(String token)
	{
		return redisTemplate.hasKey(SSOConstants.CACHE_TOKEN_PREFIX + token);
	}

	public String getOpenIdByToken(String token)
	{
		return redisTemplate.boundValueOps(SSOConstants.CACHE_TOKEN_PREFIX + token).get();
	}
}
