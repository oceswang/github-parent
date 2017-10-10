package com.github.event.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.github.event.service.EventService;

public class EventScheduler
{
	@Autowired
	EventService eventService;
	
	@Scheduled(fixedRate=500L)
	public void sendUnpublishedEvent()
	{
		eventService.sendUnpublishEvent();
	}
	@Scheduled(fixedRate=500L)
	public void handleUnprocedssEvent()
	{
		eventService.handleUnprocedssEvent();
	}
}
