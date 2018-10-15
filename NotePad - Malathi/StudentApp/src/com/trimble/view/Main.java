package com.trimble.view;

import java.util.Scanner;

import com.trimble.manager.StudentManager;
import com.trimble.model.Student;

public class Main {

	public static void main(String agrs[]) {
		int numberOfStudent;
		StudentManager studentManager = StudentManager.newInstance();
		System.out.println("Enter the Number of student");
		Scanner in = new Scanner(System.in);
		numberOfStudent = in.nextInt();
		for (int i = 0; i < numberOfStudent; i++) {

			System.out.println("Enter the name of the student" + (i + 1));
			String name = in.next();

			System.out.println("Enter the number of subject");
			int numberOfSubject = in.nextInt();

			int marks[] = new int[numberOfSubject];

			for (int j = 0; j < numberOfSubject; j++) {
				System.out.println("Enter the mark for subject " + (j + 1));
				int mark = in.nextInt();
				marks[j] = mark;
			}

			studentManager.addStudent(new Student(name, marks));
		}

		studentManager.getStudentReport();
		in.close();

	}
}
