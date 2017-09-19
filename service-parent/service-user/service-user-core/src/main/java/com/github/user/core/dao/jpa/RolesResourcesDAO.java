package com.github.user.core.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.github.user.core.entity.RolesResources;

public interface RolesResourcesDAO extends JpaRepository<RolesResources,Long>,JpaSpecificationExecutor<RolesResources>
{

}
