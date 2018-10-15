package com.trimble.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.trimble.manager.MarkManager;
import com.trimble.manager.StudentManager;
import com.trimble.model.Mark;
import com.trimble.model.Student;
import com.trimble.report.IReportGenerator;
import com.trimble.report.ReportGenerator;
import com.trimble.report.ReportGenerator.ReportType;
import com.trimble.report.StudentReport;

public class StudentApp {

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
				int externalmark = in.nextInt();
				System.out.println("Enter the internal mark for subject " + (j + 1));
				int internalmark = in.nextInt();
				Mark mark = new Mark(externalmark, internalmark, attanceMark);

				if (MarkManager.isValid(mark)) {
					marks.add(mark);
				} else {
					System.out.println("You entered a invalid mark please try again ...");
				}
			}

			studentManager.addStudent(new Student(name, marks));
		}

		IReportGenerator iReportGenerator = ReportGenerator.getInstance(ReportType.PASS_FAIL_STATUS);
		iReportGenerator.generateReport();
		
		IReportGenerator iPassReportGenerator = ReportGenerator.getInstance(ReportType.PASS_STATUS);
		iPassReportGenerator.generateReport();
		
		IReportGenerator iFailReportGenerator = ReportGenerator.getInstance(ReportType.FAIL_STATUS);
		iFailReportGenerator.generateReport();
		
		in.close();

	}
}
