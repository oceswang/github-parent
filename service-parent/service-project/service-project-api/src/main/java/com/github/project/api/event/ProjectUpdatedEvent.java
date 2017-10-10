package com.github.project.api.event;

import java.time.LocalTime;

import com.github.utils.event.constants.EventType;
import com.github.utils.event.entity.BaseEvent;

public class ProjectUpdatedEvent extends BaseEvent
{
	public static final EventType EVENT_TYPE = EventType.PROJECT_UPDATED;
	private Long projectId;
	private LocalTime eventTime;
	@Override
	public EventType getEventType()
	{
		// TODO Auto-generated method stub
		return null;
	}
	public Long getProjectId()
	{
		return projectId;
	}
	public void setProjectId(Long projectId)
	{
		this.projectId = projectId;
	}
	public LocalTime getEventTime()
	{
		return eventTime;
	}
	public void setEventTime(LocalTime eventTime)
	{
		this.eventTime = eventTime;
	}

}
