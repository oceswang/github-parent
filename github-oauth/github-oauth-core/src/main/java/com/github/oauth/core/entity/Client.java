package com.github.oauth.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.common.entity.AuditEntity;
@SuppressWarnings("serial")
@Entity
@Table(name = "t_client")
public class Client extends AuditEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "client_id")
	private String clientId;
	@Column(name = "client_key")
	private String clientKey;
	@Column(name = "client_name")
	private String clientName;
	@Column(name = "client_desc")
	private String clientDesc;
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public String getClientId()
	{
		return clientId;
	}
	public void setClientId(String clientId)
	{
		this.clientId = clientId;
	}
	public String getClientKey()
	{
		return clientKey;
	}
	public void setClientKey(String clientKey)
	{
		this.clientKey = clientKey;
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
	
	
	
	
}
