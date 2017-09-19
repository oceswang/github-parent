package com.github.user.core.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.github.user.core.entity.Resource;

public interface ResourceDAO extends JpaRepository<Resource,Long>,JpaSpecificationExecutor<Resource>
{

}
