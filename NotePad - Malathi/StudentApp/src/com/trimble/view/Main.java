package com.trimble.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.trimble.manager.StudentManager;
import com.trimble.model.Mark;
import com.trimble.model.Student;
import com.trimble.report.StudentReport;

public class Main {

	public static void main(String agrs[]) {
		int numberOfStudent;
		StudentManager studentManager = StudentManager.newInstance();
		System.out.println("Enter the Number of student");
		Scanner in = new Scanner(System.in);
		numberOfStudent = in.nextInt();
		for (int i = 0; i < numberOfStudent; i++) {

			System.out.println("Enter the name of the student " + (i + 1));
			String name = in.next();

			System.out.println("Enter the Attance Mark for student " + (i + 1));
			int attanceMark = in.nextInt();
		
			
			System.out.println("Enter the number of subject");
			int numberOfSubject = in.nextInt();

			List<Mark> marks = new ArrayList<>();
			for (int j = 0; j < numberOfSubject; j++) {
				System.out.println("Enter the external mark for subject " + (j + 1));
				int mark = in.nextInt();
				System.out.println("Enter the internal mark for subject " + (j + 1));
				int internalmark = in.nextInt();
				marks.add(new Mark( mark , internalmark  ,attanceMark));
			}

			studentManager.addStudent(new Student(name, marks));
		}

		StudentReport.getStudentReport();
		in.close();

	}
}
