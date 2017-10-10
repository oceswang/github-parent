package com.github.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.event.dao.jpa.EventProcessDAO;
import com.github.event.entity.EventProcess;
import com.github.event.entity.EventProcessStatus;

@Service
@Transactional
public class EventProcessService
{
	@Autowired
	EventProcessDAO dao;
	
	
	public int updateWithLock(Integer version, Long id)
	{
		int count = dao.updateWithLock(version, id);
		dao.flush();
		return count;
	}
	
	public EventProcess save(EventProcess entity)
	{
		return dao.save(entity);
	}
	
	public List<EventProcess> findByStatus(EventProcessStatus status)
	{
		return dao.findByStatus(status);
	}
}
