package com.example.studentmanagement.manager;

import java.util.ArrayList;
import java.util.List;

import com.example.studentmanagement.model.Student;


public class SingletonStudent {
	private List<Student> studentList=new ArrayList<>();
	public List<Student> getStudentList() {
		return studentList;
	}
	static SingletonStudent studentobj=null;
	private SingletonStudent(){
		
	}
	public static SingletonStudent getInstance(){
		if(studentobj==null){
			studentobj=new SingletonStudent();
		}
		return studentobj;
	}
}
