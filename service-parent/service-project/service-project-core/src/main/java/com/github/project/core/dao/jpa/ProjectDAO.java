package com.github.project.core.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.github.project.core.entity.Project;

public interface ProjectDAO extends JpaRepository<Project,Long>,JpaSpecificationExecutor<Project>
{
	
}
