package com.galaxyinternet.user.core.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.user.core.UserApplication;
import com.github.user.core.entity.Role;
import com.github.user.core.service.RoleService;
import com.github.user.core.service.UserService;

@RunWith(SpringRunner.class)  
@ContextConfiguration(classes = UserApplication.class)  
@SpringBootTest
public class RoleServiceTest
{
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	
	@Test
	public void queryByUserIdTest()
	{
		Long userId = 1L;
		List<Role> roles = roleService.queryByUserId(userId);
		Assert.notEmpty(roles,"Can not find roles with userId = "+userId);
	}
	@Test
	@Transactional
	@Rollback(value=false)
	public void delUserRole()
	{
		userService.delUsersRoles(2L, 3L,4L);
	}
	@Test
	@Transactional
	@Rollback(value=false)
	public void addUserRole()
	{
		userService.addUsersRoles(2L, 3L, 4L);
		
	}
	
}
