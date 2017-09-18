package com.github.user.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.user.core.dao.jpa.RoleDAO;
import com.github.user.core.dao.jpa.UserDAO;
import com.github.user.core.entity.Role;
import com.github.user.core.entity.UsersRoles;

@Service
public class RoleService
{
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private UserDAO userDAO;
	@Transactional(readOnly=true)
	public List<Role> queryByUserId(Long userId)
	{
		return Optional.ofNullable(userDAO.findOne(userId))
						.map(user -> user.getUsersRoles())
						.map(usersRoles -> usersRoles.stream().map(UsersRoles::getRole).map(Role::getRoleCode).collect(Collectors.toSet()))
						.map(roleCodes -> roleDAO.findByRoleCodeIn(roleCodes))
						.orElse(new ArrayList<>());
		
	}
	@Transactional
	public Role save(Role entity)
	{
		return roleDAO.save(entity);
	}
}
