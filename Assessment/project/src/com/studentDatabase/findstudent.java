package com.studentDatabase;

import java.util.List;

import com.student.student;

public class findstudent {

	public static student searchstudent(List<student> students, String name)
	{
		for(int i = 0; i< students.size(); i++)
		{
			if(students.get(i).getName().equals(name))
			{
				return students.get(i);
			}
		}
		return null;
	}
}
