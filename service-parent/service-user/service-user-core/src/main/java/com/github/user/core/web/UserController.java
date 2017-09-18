package com.github.user.core.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.user.api.dto.UserDTO;
import com.github.user.core.entity.User;
import com.github.user.core.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController
{
	@Autowired
	UserService userService;
	@ApiOperation(value="用户登录")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="login",value="登录名"),
			@ApiImplicitParam(name="password",value="密码，MD5加密")
	})
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public UserDTO login(@RequestParam(name="login") String login, @RequestParam(name="password") String password)
	{
		User user = userService.login(login, password);
		return convert(user);
	}
	@RequestMapping(value="/add",method=RequestMethod.GET)
	@HystrixCommand(fallbackMethod="addFallback")
	public String add()
	{
		return "success";
	}
	public String addFallback()
	{
		return "fall";
	}
	private UserDTO convert(User user)
	{
		if(user == null)
		{
			return null;
		}
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(user, dto);
		return dto;
	}
	
}
