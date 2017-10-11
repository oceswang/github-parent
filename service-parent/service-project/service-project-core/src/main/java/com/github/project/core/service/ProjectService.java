package com.github.project.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.project.api.dto.ProjectDTO;
import com.github.project.api.dto.ProjectQuery;
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
	@Transactional
	public Page<Project> query(ProjectQuery query, Pageable pageable)
	{
		Page<Project> page = dao.findAll(new Specification<Project>(){

			@Override
			public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> cq, CriteriaBuilder cb)
			{
				List<Predicate> list = new ArrayList<Predicate>();
				if(query.getCreatedId() != null)
				{
					list.add(cb.equal(root.get("createdId").as(Long.class), query.getCreatedId()));
				}
				if(query.getLine() != null)
				{
					list.add(cb.equal(root.get("line").as(String.class), query.getLine()));
				}
				if(query.getProgress() != null)
				{
					list.add(cb.equal(root.get("progress").as(String.class), query.getProgress()));
				}
				if(query.getQuery() != null)
				{
					List<Predicate> or = new ArrayList<>();
					or.add(cb.like(root.get("name").as(String.class), "%"+query.getQuery()+"%"));
					or.add(cb.like(root.get("description").as(String.class), "%"+query.getQuery()+"%"));
					Predicate[] op = new Predicate[or.size()];
					list.add(cb.or(or.toArray(op)));
				}
				
				Predicate[] p = new Predicate[list.size()]; 
				return cb.and(list.toArray(p));
			}
			
		},pageable);
		return page;
	}
}
