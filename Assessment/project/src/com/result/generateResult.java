package com.result;

import java.util.List;

import com.student.student;
import com.subject.subject;

public class generateResult {

	public boolean result(student s1)
	{
		List<subject> subjects = s1.getsubjects();
		int numberofsubjects = subjects.size();
		
		for(int i = 0; i < numberofsubjects; i++)
		{
			if(subjects.get(i).getTotalMarks()<50)
				return false;
		}
		return true;
	}
}
