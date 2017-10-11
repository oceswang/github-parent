package com.github.user.core.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.user.api.dto.UserDTO;
import com.github.user.core.entity.User;
import com.github.user.core.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
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
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity query(@RequestBody UserDTO dto)
	{
		return null;
	}
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ResponseEntity add(@RequestBody UserDTO dto)
	{
		return null;
	}
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity getById(@PathVariable Long id)
	{
		return null;
	}
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public ResponseEntity update(@PathVariable Long id)
	{
		return null;
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
