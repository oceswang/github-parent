package com.github.user.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_roles_resources")
public class RolesResources
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name="resource_code",referencedColumnName="resource_code")
	private Resource resource;
	
	@ManyToOne
	@JoinColumn(name="role_code",referencedColumnName="role_code")
	private Role role;
	
	@Column(name = "resource_order")
	private Integer resourceOrder;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Resource getResource()
	{
		return resource;
	}

	public void setResource(Resource resource)
	{
		this.resource = resource;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	public Integer getResourceOrder()
	{
		return resourceOrder;
	}

	public void setResourceOrder(Integer resourceOrder)
	{
		this.resourceOrder = resourceOrder;
	}
	
	
}
