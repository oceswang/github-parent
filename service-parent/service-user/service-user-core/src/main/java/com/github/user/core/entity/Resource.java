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
@Table(name = "t_resource")
public class Resource extends AuditEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	private Set<RolesResources> rolesResources;

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

	public Set<RolesResources> getRolesResources()
	{
		return rolesResources;
	}

	public void setRolesResources(Set<RolesResources> rolesResources)
	{
		this.rolesResources = rolesResources;
	}
}
