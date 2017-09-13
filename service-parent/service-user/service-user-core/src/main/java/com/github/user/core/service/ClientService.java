package com.github.user.core.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.user.core.dao.jpa.ClientDAO;
import com.github.user.core.entity.Client;
@Service
public class ClientService
{
	@Autowired
	private ClientDAO clientDAO;

	@Transactional(readOnly=true)
	public Client getByClientId(String clientId)
	{
		return clientDAO.getByClientId(clientId);
	}
	@Transactional(readOnly=true)
	public Client getByClientIdAndClientSecret(String clientId, String clientSecret)
	{
		return clientDAO.getByClientIdAndClientSecret(clientId, clientSecret);
	}
	
}
