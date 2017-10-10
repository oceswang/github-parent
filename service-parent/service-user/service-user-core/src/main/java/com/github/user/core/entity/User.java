package com.github.user.core.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.github.common.entity.AuditEntity;

@Entity
@Table(name = "t_user")
public class User extends AuditEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "login", updatable = false)
	private String login;

	@Column(name = "password")
	private String password;

	@Column(name = "real_name")
	private String realName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "active")
	private Byte active;

	@Column(name = "latest_login")
	private LocalDateTime latestLogin;
	
	@Column(name = "open_id")
	private String openId;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "t_users_clients", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "client_id"))
	private Set<Client> clients;
	
	@OneToMany(mappedBy = "user")
	private Set<UsersRoles> usersRoles;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getRealName()
	{
		return realName;
	}

	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public Byte getActive()
	{
		return active;
	}

	public void setActive(Byte active)
	{
		this.active = active;
	}

	public LocalDateTime getLatestLogin()
	{
		return latestLogin;
	}

	public void setLatestLogin(LocalDateTime latestLogin)
	{
		this.latestLogin = latestLogin;
	}

	public String getOpenId()
	{
		return openId;
	}

	public void setOpenId(String openId)
	{
		this.openId = openId;
	}

	public Set<Client> getClients()
	{
		return clients;
	}

	public void setClients(Set<Client> clients)
	{
		this.clients = clients;
	}

	public Set<UsersRoles> getUsersRoles()
	{
		return usersRoles;
	}

	public void setUsersRoles(Set<UsersRoles> usersRoles)
	{
		this.usersRoles = usersRoles;
	}
	

}
