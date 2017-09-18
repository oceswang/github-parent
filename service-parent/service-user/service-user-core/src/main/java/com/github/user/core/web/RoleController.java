package com.github.user.core.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.user.api.dto.RoleDTO;
import com.github.user.core.entity.Role;
import com.github.user.core.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController
{
	@Autowired
	private RoleService roleService;
	@RequestMapping(value="/queryByUserId/{userId}",method=RequestMethod.GET)
	public List<RoleDTO> queryByUserId(@PathVariable(name="userId")Long userId)
	{
		
		return convert(roleService.queryByUserId(userId));
	}
	
	private List<RoleDTO> convert(List<Role> src)
	{
		List<RoleDTO> tar = new ArrayList<>(src.size());
		src.forEach(item -> {
			RoleDTO dto = new RoleDTO();
			BeanUtils.copyProperties(item, dto);
			tar.add(dto);
		});
		return tar;
	}
}
