package com.github.project.core.web;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.event.service.EventService;
import com.github.project.api.dto.ProjectDTO;
import com.github.project.api.dto.ProjectQuery;
import com.github.project.api.event.ProjectCreatedEvent;
import com.github.project.api.event.ProjectUpdatedEvent;
import com.github.project.core.entity.Project;
import com.github.project.core.service.ProjectService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController
{
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	@Autowired
	ProjectService projectService;
	@Autowired
	EventService eventService;

	@ApiOperation(value = "搜索项目")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity query(ProjectQuery query, @PageableDefault Pageable pageable)
	{
		try
		{
			Page<Project> page = projectService.query(query, pageable);
			Page<ProjectDTO> dtoPage = page.map(new Converter<Project,ProjectDTO>(){

				@Override
				public ProjectDTO convert(Project source)
				{
					ProjectDTO target = new ProjectDTO();
					BeanUtils.copyProperties(source, target);
					return target;
				}
			});
			return ResponseEntity.ok(dtoPage);
		} catch (Exception e)
		{
			logger.error("Query error, Query="+query);
			return ResponseEntity.badRequest().build();
		}
	}
	@ApiOperation(value = "添加项目")
	@ApiImplicitParams(value = { @ApiImplicitParam(name = "dto", value = "项目信息", dataType = "ProjectDTO") })
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity add(@RequestBody ProjectDTO dto)
	{
		Project entity = projectService.save(dto);
		if(dto.getId() != null)
		{
			ProjectCreatedEvent event = new ProjectCreatedEvent();
			event.setProjectId(entity.getId());
			event.setEventTime(LocalTime.now());
			eventService.publish(event);
		}
		else
		{
			ProjectUpdatedEvent event = new ProjectUpdatedEvent();
			event.setProjectId(entity.getId());
			event.setEventTime(LocalTime.now());
			eventService.publish(event);
		}
		ProjectDTO rtn = new ProjectDTO();
		BeanUtils.copyProperties(entity, rtn);
		return null;
	}
	@ApiOperation(value = "根据ID查询项目")
	@ApiImplicitParams(value = { @ApiImplicitParam(name = "id", value = "项目ID", paramType="path") })
	@HystrixCommand(fallbackMethod="getFallback")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getById(@PathVariable(name = "id") Long id)
	{
		Project entity = projectService.getById(id);
		ProjectDTO rtn = new ProjectDTO();
		BeanUtils.copyProperties(entity, rtn);
		return null;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity update(@PathVariable(name = "id") Long id)
	{
		return null;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable(name = "id") Long id)
	{
		return null;
	}
	
	
	
	public ProjectDTO getFallback(@PathVariable(name = "id") Long id)
	{
		return null;
	}

}
