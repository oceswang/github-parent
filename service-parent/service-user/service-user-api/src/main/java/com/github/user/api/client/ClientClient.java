package com.github.user.api.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service")
public interface ClientClient
{
	@RequestMapping(value="/client/checkClient", method=RequestMethod.GET)
	public boolean checkClient(@RequestParam(value="clientId") String clientId);
	
	@RequestMapping(value="/client/checkClientAndSecret", method=RequestMethod.GET)
	public boolean checkClientAndSecret(@RequestParam(value="clientId") String clientId, @RequestParam(value="clientSecret") String clientSecret);
}
