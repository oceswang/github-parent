package com.github.user.core.dao.jpa;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.github.user.core.entity.Role;

public interface RoleDAO extends JpaRepository<Role,Long>,JpaSpecificationExecutor<Role>
{
	List<Role> findByRoleCodeIn(Set<String> roleCodes);
}
