package com.student;

import java.util.ArrayList;
import java.util.List;

import com.subject.subject;

public class student {
	
	private String name;
	private List<subject> subjects = new ArrayList<subject>(); 
	
	public student()
	{
		this.name = "student";
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}
	public List<subject> getsubjects()
	{
		return subjects;
	}
	public void addsubject(subject sub)
	{
		subjects.add(sub);
	}
	
}
