package com.github.project.core.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.project.api.dto.ProjectDTO;
import com.github.project.core.entity.Project;
import com.github.project.core.service.ProjectService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/project")
public class ProjectController
{
	@Autowired
	ProjectService projectService;

	@ApiOperation(value = "保存项目")
	@ApiImplicitParams(value = { @ApiImplicitParam(name = "dto", value = "项目信息", dataType = "ProjectDTO") })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ProjectDTO save(@RequestBody ProjectDTO dto)
	{
		Project entity = projectService.save(dto);
		ProjectDTO rtn = new ProjectDTO();
		BeanUtils.copyProperties(entity, rtn);
		return rtn;
	}
	@ApiOperation(value = "根据ID查询项目")
	@ApiImplicitParams(value = { @ApiImplicitParam(name = "id", value = "项目ID", paramType="path") })
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod="getFallback")
	public ProjectDTO get(@PathVariable(name = "id") Long id)
	{
		Project entity = projectService.getById(id);
		ProjectDTO rtn = new ProjectDTO();
		BeanUtils.copyProperties(entity, rtn);
		return rtn;
	}
	public ProjectDTO getFallback(@PathVariable(name = "id") Long id)
	{
		return null;
	}

}
