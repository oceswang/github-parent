package com.github.user.core.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.user.core.dao.jpa.ResourceDAO;
import com.github.user.core.dao.jpa.RoleDAO;
import com.github.user.core.dao.jpa.UserDAO;
import com.github.user.core.entity.Resource;
import com.github.user.core.entity.Role;
import com.github.user.core.entity.RolesResources;
import com.github.user.core.entity.User;
import com.github.user.core.entity.UsersRoles;

@Service
public class ResourceService
{
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private ResourceDAO resourceDAO;

	@Transactional
	public Set<Resource> getByRoleId(Long roleId)
	{
		return Optional.of(roleDAO.findOne(roleId)).map(Role::getRolesResources).map(
				set -> set.stream().map(RolesResources::getResource).collect(Collectors.toSet())).orElse(
						new HashSet<>());
	}

	@Transactional
	public Set<Resource> getByUserId(Long userId)
	{
		Set<Role> roles = Optional.ofNullable(userDAO.findOne(userId)).map(User::getUsersRoles).map(
				urSet -> urSet.stream().map(UsersRoles::getRole).collect(Collectors.toSet())).orElse(new HashSet<>());

		Set<RolesResources> rrSet = new HashSet<>();
		roles.stream().map(Role::getRolesResources).collect(Collectors.toSet()).forEach(rrSet::addAll);
		return rrSet.stream().map(RolesResources::getResource).collect(Collectors.toSet());
	}
	@Transactional
	public Resource save(@RequestBody Resource entity)
	{
		return resourceDAO.save(entity);
	}
	
	
}
