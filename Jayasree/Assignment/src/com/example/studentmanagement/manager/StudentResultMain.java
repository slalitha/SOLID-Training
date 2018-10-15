package com.example.studentmanagement.manager;

import java.util.ArrayList;
import java.util.List;

public class StudentResultMain extends StudentResult implements IMark{

	@Override
	public List<Integer> getTotalMarks(List<Integer> marks,int attendance,int internal) {
		List<Integer> totalmarks=new ArrayList<>();
		for(int i=0;i<marks.size();i++){
			double mark=marks.get(i)/100.0;
			totalmarks.add((int) (mark*70)+attendance+internal);
		}
		return totalmarks;
		
	}

	@Override
	public String getStudentResult(List<Integer> totalmarks) {
		for(int i=0;i<totalmarks.size();i++){
			if(totalmarks.get(i)<50)
				return "Fail";
		}
		return "Pass";
	}
	
}
