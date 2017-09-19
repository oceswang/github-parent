package com.github.user.core.web;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.user.api.dto.ResourceDTO;
import com.github.user.core.entity.Resource;
import com.github.user.core.service.ResourceService;

@RestController
@RequestMapping("/resources")
public class ResourceController
{
	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "getByUserId/{userId}", method = RequestMethod.GET)
	public Set<ResourceDTO> getByUserId(@PathVariable(name = "userId") Long userId)
	{
		return convert(resourceService.getByUserId(userId));
	}

	@RequestMapping(value = "getByUserId/{roleId}", method = RequestMethod.GET)
	public Set<ResourceDTO> getByRoleId(@PathVariable(name = "roleId") Long roleId)
	{
		return convert(resourceService.getByRoleId(roleId));
	}
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ResourceDTO save(@RequestBody ResourceDTO dto)
	{
		Resource entity = convert(dto);
		Resource po = resourceService.save(entity);
		return convert(po);
	}

	private Set<ResourceDTO> convert(Set<Resource> src)
	{
		Set<ResourceDTO> target = new HashSet<>(src.size());
		Optional.ofNullable(src).orElse(new HashSet<>()).stream().forEach(item -> {
			ResourceDTO dto = new ResourceDTO();
			BeanUtils.copyProperties(item, dto);
			target.add(dto);
		});
		return target;
	}
	private Resource convert(ResourceDTO src)
	{
		if(src == null) return null;
		Resource entity = new Resource();
		BeanUtils.copyProperties(src, entity);
		return entity;
	}
	private ResourceDTO convert(Resource src)
	{
		if(src == null) return null;
		ResourceDTO dto = new ResourceDTO();
		BeanUtils.copyProperties(src, dto);
		return dto;
	}
	
}
