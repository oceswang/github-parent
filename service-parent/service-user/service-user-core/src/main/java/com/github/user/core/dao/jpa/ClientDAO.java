package com.github.user.core.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.github.user.core.entity.Client;

public interface ClientDAO extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client	>
{
	public Client getByClientId(String clientId);
	public Client getByClientIdAndClientSecret(String clientId, String clientSecret);
}
