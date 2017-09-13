package com.github.project.api.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.project.api.dto.ProjectDTO;
@FeignClient("project-service")
public interface ProjectClient
{
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ProjectDTO save(@RequestBody ProjectDTO dto);
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ProjectDTO save(@PathVariable(name="id") Long id);
}
