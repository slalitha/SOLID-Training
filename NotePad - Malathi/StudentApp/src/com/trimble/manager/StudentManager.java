package com.trimble.manager;

import java.util.List;

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
		boolean isPass = true;
		int[] marks = student.getmMarks();
		for (int mark : marks) {
			if (mark < 50) {
				isPass = false;
			}
		}
		return isPass;
	}

	public void getStudentReport() {

		System.out.println("********************************************");
		System.out.println("\t     Student Report");
		System.out.println("********************************************");
		System.out.println(" RollNo\t   Name\t\tPassStatus");
		System.out.println("********************************************");
		for (int i = 0; i < mStudentList.size(); i++) {
			Student student = mStudentList.get(i);
			System.out.print(" "+student.getmRollno()+"\t   "+student.getmName() + "\t\t");
			if (isSudentHasPass(student)) {
				System.out.println("Pass");
			} else {
				System.out.println("Fail");
			}
		}
	}
}
