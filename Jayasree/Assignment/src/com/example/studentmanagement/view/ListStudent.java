package com.example.studentmanagement.view;

import java.util.List;

import com.example.studentmanagement.model.Student;

public class ListStudent {
	public void display(List<Student> student){
		System.out.println("Index\t\t\tStudent name\t\t\tMarks");
		System.out.println("---------------------------------------------------------------------------");
		for(int i=0;i<student.size();i++){
			System.out.println(i+1+"\t\t\t"+student.get(i).getName()+"\t\t\t"+student.get(i).getMarks());
		}
	}
}
