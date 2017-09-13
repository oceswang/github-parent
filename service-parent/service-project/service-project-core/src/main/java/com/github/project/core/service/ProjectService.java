package com.github.project.core.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.project.api.dto.ProjectDTO;
import com.github.project.core.dao.jpa.ProjectDAO;
import com.github.project.core.entity.Project;

@Service
public class ProjectService
{
	@Autowired
	private ProjectDAO dao;
	
	@Transactional
	public Project save(ProjectDTO dto)
	{
		Project entity = new Project();
		BeanUtils.copyProperties(dto, entity);
		return dao.save(entity);
	}
	@Transactional
	public Project getById(Long id)
	{
		return dao.findOne(id);
	}
	
}
