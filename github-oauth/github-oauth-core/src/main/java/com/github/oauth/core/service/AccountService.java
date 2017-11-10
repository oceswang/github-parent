package com.github.oauth.core.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.oauth.core.dao.jpa.AccountDAO;
import com.github.oauth.core.entity.Account;
import com.github.oauth.core.exception.AuthorizeException;

@Service
public class AccountService
{
	@Autowired
	private AccountDAO accountDAO;
	
	@Transactional
	public Account login(String login, String password)
	{
		if(StringUtils.isEmpty(login) || StringUtils.isEmpty(password))
		{
			throw new AuthorizeException("用户名/密码不能为空");
		}
		Account account = accountDAO.getByEmail(login);
		if(account == null)
		{
			account = accountDAO.getByPhone(login);
		}
		if(account == null || !password.equals(account.getPassword()))
		{
			throw new AuthorizeException("用户名/密码不正确");
		}
		
		return account;
	}
}
