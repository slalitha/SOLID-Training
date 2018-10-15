package com.trimble.report;

import java.util.List;

import com.trimble.manager.StudentManager;
import com.trimble.model.Student;

public class StudentReport {

	public static void getStudentReport() {

		StudentManager studentManager = StudentManager.newInstance();
		List<Student> mStudentList = studentManager.getStudentList();
		System.out.println("********************************************");
		System.out.println("\t     Student Report");
		System.out.println("********************************************");
		System.out.println(" RollNo\t   Name\t\tPassStatus");
		System.out.println("********************************************");
		for (int i = 0; i < mStudentList.size(); i++) {
			Student student = mStudentList.get(i);
			System.out.print(" "+student.getmRollno()+"\t   "+student.getmName() + "\t\t");
			if (StudentManager.newInstance().isSudentHasPass(student)) {
				System.out.println("Pass");
			} else {
				System.out.println("Fail");
			}
		}
	}
}
