package com.github.user.api.dto;

import java.time.LocalDateTime;

public class UserDTO
{
	private Long id;

	private String login;

	private String realName;

	private String email;

	private String phone;

	private Byte active;

	private LocalDateTime latestLogin;
	
	private String openId;

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
}
