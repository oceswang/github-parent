package com.github.user.core.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.github.common.entity.AuditEntity;

@Entity
@Table(name = "t_client")
public class Client extends AuditEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "client_name")
	private String clientName;
	
	@Column(name = "client_desc")
	private String clientDesc;
	
	@Column(name = "client_id")
	private String clientId;
	
	@Column(name = "client_secret")
	private String clientSecret;
	
	@ManyToMany(cascade = CascadeType.PERSIST, mappedBy="clients")
	private Set<User> users;

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
