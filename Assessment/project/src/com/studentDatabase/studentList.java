package com.studentDatabase;

import java.util.ArrayList;
import java.util.List;

import com.result.generateResult;
import com.student.student;

public class studentList {
	
	private List<student> students =  new ArrayList<student>(); 
	
	public void addstudent(student s1)
	{
		students.add(s1);
	}
	public List<student> getstudentList()
	{
		return students;
	}
	public boolean getresult(String name)
	{
		student s1 = findstudent.searchstudent(students, name);
		if(s1!=null)
		{
			  generateResult finalresult = new generateResult();
				return finalresult.result(s1);
		}
		return false;
	}
}
