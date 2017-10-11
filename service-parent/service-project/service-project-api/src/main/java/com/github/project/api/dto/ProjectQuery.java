package com.github.project.api.dto;

public class ProjectQuery
{
	private String line;
	
	private String progress;
	
	private Long createdId;
	
	private String query;

	public String getLine()
	{
		return line;
	}

	public void setLine(String line)
	{
		this.line = line;
	}

	public String getProgress()
	{
		return progress;
	}

	public void setProgress(String progress)
	{
		this.progress = progress;
	}

	public Long getCreatedId()
	{
		return createdId;
	}

	public void setCreatedId(Long createdId)
	{
		this.createdId = createdId;
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

	@Override
	public String toString()
	{
		return "ProjectQuery [line=" + line + ", progress=" + progress + ", createdId=" + createdId + ", query=" + query
				+ "]";
	}
	
	
}
