package com.github.user.api.client;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.user.api.dto.UserDTO;
@FeignClient("user-service")
public interface UserClient
{
	@RequestMapping(value="/user/login", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public UserDTO login(@RequestParam(name="login") String login, @RequestParam(name="password") String password);
}
