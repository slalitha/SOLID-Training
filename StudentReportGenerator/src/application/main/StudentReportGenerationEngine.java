package application.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import application.structure.Mark;
import application.structure.Student;
import application.structure.StudentReport;
import application.structure.Subject;
import application.utils.Constants;
import application.utils.IReportGenerator;
import application.utils.ReportGeneratorProviderFactory;

public class StudentReportGenerationEngine {
	
	
	public static void main(String[] args) {
		System.out.println(Constants.WELCOME_MSG);
		int exitStatus = 1;
		do {
			StudentReport studentReport = getInputAndInitializeReport();
			application.utils.ResultGenerator.IReportGenerator reportGenerator = ReportGeneratorProviderFactory.getReportGenerator(Constants.NORMAL);
			reportGenerator.calculateGrade(studentReport);
			reportGenerator.displayResults(studentReport);
			System.out.println(Constants.EXIT_MSG);
			Scanner scanner = new Scanner(System.in);
			exitStatus = scanner.nextInt();
		} while(exitStatus != 0);
	}
	
	private static StudentReport getInputAndInitializeReport() {
		System.out.println(Constants.NAME);
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		System.out.println(Constants.NO_OF_SUBJECTS);
		int no_of_subs = scanner.nextInt();
		Map<Subject,Mark> subjectMarkMap = new HashMap<>();
		for(int i=0; i< no_of_subs; i++) {
			System.out.println(Constants.SUBJECT_TITLE);
			String subject_title = scanner.next();
			System.out.println(Constants.ATTENDANCE);
			int attendance = scanner.nextInt();
			System.out.println(Constants.INTERNALS);
			int internalMarks = scanner.nextInt();
			System.out.println(Constants.EXTERNALS);
			int externalMarks = scanner.nextInt();
			subjectMarkMap.put(intializeSubject(subject_title), initializeMark(attendance,internalMarks,externalMarks));
		}
		return initializeReport(initializeStudent(name), subjectMarkMap);
	}
	
	private static Student initializeStudent(String name) {
		Student student = new Student();
		student.setName(name);
		return student;
	}
	
	private static Subject intializeSubject(String title) {
		Subject subject = new Subject();
		subject.setTitle(title);
		return subject;
	}
	
	private static StudentReport initializeReport(Student student, Map<Subject, Mark> subjectMarkMap) {
		StudentReport report = new StudentReport();
		report.setStudent(student);
		report.setSubjecMarkMap(subjectMarkMap);
		return report;
	}
	
	private static Mark initializeMark(int attendance, int internalMarks, int externalMarks) {
		Mark mark = new Mark();
		mark.setAttendance(attendance);
		mark.setExternalMarks(externalMarks);
		mark.setInternalMarks(internalMarks);
		return mark;
	}
	
}
