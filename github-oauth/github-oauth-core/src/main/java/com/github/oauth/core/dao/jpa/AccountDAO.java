package com.github.oauth.core.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.github.oauth.core.entity.Account;

public interface AccountDAO extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account>
{
	public Account getByEmail(String email);
	
	public Account getByPhone(String phone);

}
