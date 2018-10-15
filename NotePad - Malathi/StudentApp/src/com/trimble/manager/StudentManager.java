package com.trimble.manager;

import java.util.List;

import com.trimble.model.Mark;
import com.trimble.model.Student;

import java.util.ArrayList;

public class StudentManager {

	private static StudentManager mStudentManager = new StudentManager();
	private List<Student> mStudentList = new ArrayList<>();

	public static StudentManager newInstance() {
		return mStudentManager;
	}

	public void addStudent(Student student) {
		mStudentList.add(student);
	}

	public Student getStudent(int index) {
		return mStudentList.get(index);
	}

	public List<Student> getStudentList() {
		return mStudentList;
	}

	public boolean isSudentHasPass(Student student) {
		return MarkManager.isPass(student.getmMarks());
	}
}
