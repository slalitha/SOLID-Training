package com.result;

import com.studentDatabase.studentList;

public class displayResult {

	public static void displayresult(studentList studentlist, String name)
	{
		String result = name + " ";
		result = result.concat((studentlist.getresult(name)?"pass":"fail") + '\n');
		System.out.print(result);
	}
}