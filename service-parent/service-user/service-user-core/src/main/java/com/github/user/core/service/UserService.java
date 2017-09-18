package com.github.user.core.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.common.exception.BusinessException;
import com.github.user.core.dao.jpa.RoleDAO;
import com.github.user.core.dao.jpa.UserDAO;
import com.github.user.core.dao.jpa.UsersRolesDAO;
import com.github.user.core.entity.Role;
import com.github.user.core.entity.User;
import com.github.user.core.entity.UsersRoles;

@Service
public class UserService
{
	@Autowired
	private UserDAO dao;
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private UsersRolesDAO usersRolesDAO;
	
	
	@Transactional
	public User login(String login, String password)
	{
		if(StringUtils.isEmpty(login) || StringUtils.isEmpty(password))
		{
			throw new BusinessException("用户名/密码不能为空");
		}
		User user = dao.findOne(new Specification<User>(){

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb)
			{
				List<Predicate> list = new ArrayList<>();
				list.add(cb.or(cb.equal(root.get("login"), login)));
				list.add(cb.or(cb.equal(root.get("email"), login)));
				list.add(cb.or(cb.equal(root.get("phone"), login)));
				Predicate[] predicates = new Predicate[list.size()];
				predicates = list.toArray(predicates);
				return cb.or(predicates);
			}
			
		});
		if(user == null || !password.equals(user.getPassword()))
		{
			throw new BusinessException("用户名/密码不正确");
		}
		user.setLatestLogin(LocalDateTime.now());
		dao.save(user);
		
		return user;
			
	}
	@Transactional
	public User save(User user)
	{
		return dao.save(user);
	}
	@Transactional
	public User findOne(Long id)
	{
		return dao.findOne(id);
	}
	@Transactional
	public User delUsersRoles(Long userId, Long... roleIds)
	{
		User user = dao.findOne(userId);
		user.getUsersRoles().stream().forEach( ur -> {
			Arrays.asList(roleIds).stream().forEach(roleId -> {
				if(roleId.equals(ur.getRole().getId()))
				{
					usersRolesDAO.delete(ur.getId());
				}
			});
		});
		return user;
	}
	@Transactional
	public User addUsersRoles(Long userId, Long... roleIds)
	{
		User user = dao.findOne(userId);
		usersRolesDAO.delete(user.getUsersRoles());
		Arrays.asList(roleIds).stream().forEach(roleId -> {
			Role role = roleDAO.findOne(roleId);
			if(role != null)
			{
				UsersRoles item = new UsersRoles();
				item.setRole(role);
				item.setUser(user);
				usersRolesDAO.save(item);
			}
		});
		
		return user;
	}
	
	
	
	
}
