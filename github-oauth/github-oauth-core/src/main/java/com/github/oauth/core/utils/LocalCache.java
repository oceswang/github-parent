package com.github.oauth.core.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocalCache
{
	private Map<String,Object> cache = new ConcurrentHashMap<>();
	private static LocalCache instance = new LocalCache();
	private LocalCache(){}
	public static LocalCache getInstance()
	{
		return instance;
	}
	
	public void set(String key,Object value)
	{
		cache.put(key, value);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key)
	{
		return (T)cache.get(key);
	}
	public boolean exists(String key)
	{
		return cache.containsKey(key);
	}
}
