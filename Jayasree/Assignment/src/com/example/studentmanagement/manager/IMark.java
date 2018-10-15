package com.example.studentmanagement.manager;

import java.util.List;

public interface IMark {
	public List<Integer> getTotalMarks(List<Integer> marks,int attendance,int internal);
}
