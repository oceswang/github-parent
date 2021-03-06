package com.github.user.core.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.github.common.entity.AuditEntity;

@Entity
@Table(name = "t_role")
public class Role extends AuditEntity 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "role_code", updatable = false)
	private String roleCode;
	
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "role_desc")
	private String roleDesc;
	
	@OneToMany(mappedBy="role")
	private Set<UsersRoles> usersRoles;
	
	@OneToMany(mappedBy="role")
	private Set<RolesResources> rolesResources;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getRoleCode()
	{
		return roleCode;
	}

	public void setRoleCode(String roleCode)
	{
		this.roleCode = roleCode;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public String getRoleDesc()
	{
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc)
	{
		this.roleDesc = roleDesc;
	}

	public Set<UsersRoles> getUsersRoles()
	{
		return usersRoles;
	}

	public void setUsersRoles(Set<UsersRoles> usersRoles)
	{
		this.usersRoles = usersRoles;
	}

	public Set<RolesResources> getRolesResources()
	{
		return rolesResources;
	}

	public void setRolesResources(Set<RolesResources> rolesResources)
	{
		this.rolesResources = rolesResources;
	}

	
}
