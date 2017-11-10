package com.github.oauth.core.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.oauth.core.dao.jpa.ClientDAO;
import com.github.oauth.core.entity.Client;

@Service
public class ClientService
{
	@Autowired
	private ClientDAO clientDAO;
	
	@Transactional
	public boolean checkClient(String clientId)
	{
		if(StringUtils.isEmpty(clientId))
		{
			return false;
		}
		Client app = clientDAO.getByClientId(clientId);
		return app != null;
	}
	
	@Transactional
	public boolean checkClient(String clientId, String clientKey)
	{
		if(StringUtils.isEmpty(clientId) || StringUtils.isEmpty(clientKey))
		{
			return false;
		}
		Client app = clientDAO.getByClientId(clientId);
		if(app != null && clientKey.equals(app.getClientKey()))
		{
			return true;
		}
		return false;
	}
}
