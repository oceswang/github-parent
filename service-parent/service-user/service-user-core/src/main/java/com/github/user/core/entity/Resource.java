package com.github.user.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_resource")
public class Resource
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "resource_code", updatable = false)
	private String resourceCode;
	
	@Column(name = "resource_name")
	private String resourceName;
	
	@Column(name = "resource_desc")
	private String resourceDesc;
	
	@OneToMany(mappedBy="resource")
	private RolesResources rolesResources;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getResourceCode()
	{
		return resourceCode;
	}

	public void setResourceCode(String resourceCode)
	{
		this.resourceCode = resourceCode;
	}

	public String getResourceName()
	{
		return resourceName;
	}

	public void setResourceName(String resourceName)
	{
		this.resourceName = resourceName;
	}

	public String getResourceDesc()
	{
		return resourceDesc;
	}

	public void setResourceDesc(String resourceDesc)
	{
		this.resourceDesc = resourceDesc;
	}
	
	
	
	
}
