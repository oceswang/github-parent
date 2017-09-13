package com.github.user.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.user.core.entity.Client;
import com.github.user.core.service.ClientService;
@RestController
@RequestMapping("/client")
public class ClientController
{
	@Autowired
	ClientService clientService;
	@RequestMapping(value="/checkClient", method=RequestMethod.GET)
	public boolean checkClient(String clientId)
	{
		Client client = clientService.getByClientId(clientId);
		return client != null;
	}
	@RequestMapping(value="/checkClientAndSecret", method=RequestMethod.GET)
	public boolean checkClientAndSecret(String clientId, String clientSecret)
	{
		Client client = clientService.getByClientIdAndClientSecret(clientId, clientSecret);
		return client != null;
	}
}
