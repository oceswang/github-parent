package com.galaxyinternet.user.core.service;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.github.user.core.UserApplication;
import com.github.user.core.entity.Resource;
import com.github.user.core.service.ResourceService;

@RunWith(SpringRunner.class)  
@ContextConfiguration(classes = UserApplication.class)  
@SpringBootTest
public class ResourceServiceTest
{

	@Autowired
	ResourceService resourceService;
	
	@Test
	public void getByRoleId()
	{
		Long roleId = 1l;
		Set<Resource> set = resourceService.getByRoleId(roleId);
		Assert.notEmpty(set,"Resource is null with role id ="+roleId);
	}
	@Test
	public void getByUserId()
	{
		Long userId = 1l;
		Set<Resource> set = resourceService.getByUserId(userId);
		Assert.notEmpty(set,"Resource is null with user id ="+userId);
	}
}
