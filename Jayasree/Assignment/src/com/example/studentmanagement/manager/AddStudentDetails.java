package com.example.studentmanagement.manager;

import com.example.studentmanagement.model.Student;

public class AddStudentDetails {

	public void addStudent(Student student){
		SingletonStudent.getInstance().getStudentList().add(student);
	}
}
