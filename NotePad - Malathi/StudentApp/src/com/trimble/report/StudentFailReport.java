package com.trimble.report;

import java.util.List;

import com.trimble.manager.StudentManager;
import com.trimble.model.Student;

public class StudentFailReport implements IReportGenerator {
	
	@Override
	public void generateReport() {
		StudentManager studentManager = StudentManager.newInstance();
		List<Student> mStudentList = studentManager.getStudentList();
		System.out.println("********************************************");
		System.out.println("\t     Fail Student Report");
		System.out.println("********************************************");
		System.out.println(" RollNo\t   Name\n");
		System.out.println("********************************************");
		for (int i = 0; i < mStudentList.size(); i++) {
			Student student = mStudentList.get(i);
			if (!studentManager.isSudentHasPass(student)) {
				System.out.print(" "+student.getmRollno()+"\t   "+student.getmName());
			}
		}
	}

}
