package com.github.user.api.dto;

public class ClientDTO
{
	private Long id;
	
	private String clientName;
	
	private String clientDesc;
	
	private String clientId;
	
	private String clientSecret;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getClientName()
	{
		return clientName;
	}

	public void setClientName(String clientName)
	{
		this.clientName = clientName;
	}

	public String getClientDesc()
	{
		return clientDesc;
	}

	public void setClientDesc(String clientDesc)
	{
		this.clientDesc = clientDesc;
	}

	public String getClientId()
	{
		return clientId;
	}

	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}

	public String getClientSecret()
	{
		return clientSecret;
	}

	public void setClientSecret(String clientSecret)
	{
		this.clientSecret = clientSecret;
	}
	
	
}
