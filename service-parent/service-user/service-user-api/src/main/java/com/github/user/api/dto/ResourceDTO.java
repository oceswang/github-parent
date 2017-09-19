package com.github.user.api.dto;

public class ResourceDTO
{
	private Long id;
	
	private String resourceCode;
	
	private String resourceName;
	
	private String resourceDesc;

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
