package com.example.studentmanagement.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.studentmanagement.manager.AddStudentDetails;
import com.example.studentmanagement.model.Student;

public class AddStudent {
	public void addStudent(){
		@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
		List<Integer> marks=new ArrayList<>();
		System.out.println("Enter Student name:");
		String name=in.next();
		System.out.println("Enter the marks for 5 subjects:");
		for(int i=0;i<5;i++){
			marks.add(in.nextInt());
		}
		AddStudentDetails addstudentdetails=new AddStudentDetails();
		addstudentdetails.addStudent(new Student(name,marks));
	}
}
