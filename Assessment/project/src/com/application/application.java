package com.application;

import com.result.displayResult;
import com.student.student;
import com.studentDatabase.studentList;
import com.subject.subject;

public class application {

	public static void main(String[] args0)
	{
		studentList studentlist = new studentList();
		
		student s1 = new student();
		s1.setName("Vimal");
		s1.addsubject(new subject("Chemistry",10,20,30));
		s1.addsubject(new subject("Physics",10,20,30));
		s1.addsubject(new subject("Mathematics",10,20,30));
		s1.addsubject(new subject("Computer",10,20,30));
		student s2 = new student();
		s2.setName("Akash");
		s2.addsubject(new subject("Chemistry",10,10,30));
		s2.addsubject(new subject("Physics",10,10,30));
		s2.addsubject(new subject("Mathematics",10,20,30));
		s2.addsubject(new subject("Computer",10,10,30));
		student s3 = new student();
		s3.setName("Puneet");
		s3.addsubject(new subject("Chemistry",10,10,30));
		s3.addsubject(new subject("Physics",10,10,30));
		s3.addsubject(new subject("Mathematics",10,20,30));
		s3.addsubject(new subject("Computer",10,2,30));
		studentlist.addstudent(s1);
		studentlist.addstudent(s2);
		studentlist.addstudent(s3);
		
		displayResult.displayresult(studentlist, "Puneet");
		displayResult.displayresult(studentlist, "Vimal");
		displayResult.displayresult(studentlist, "Akash");
		
	}
	
	
}
