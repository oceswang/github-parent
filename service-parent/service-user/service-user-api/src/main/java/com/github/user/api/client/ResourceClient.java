package com.github.user.api.client;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.user.api.dto.ResourceDTO;
import com.github.user.api.dto.RoleDTO;

@FeignClient("user-service")
public interface ResourceClient
{
	@RequestMapping(value = "resource/queryByUserId/{userId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public List<RoleDTO> queryByUserId(@PathVariable(name = "userId") Long userId);

	@RequestMapping(value = "resource/getByUserId/{roleId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public Set<ResourceDTO> getByRoleId(@PathVariable(name = "roleId") Long roleId);

	@RequestMapping(value = "save", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResourceDTO save(@RequestBody ResourceDTO dto);
}
