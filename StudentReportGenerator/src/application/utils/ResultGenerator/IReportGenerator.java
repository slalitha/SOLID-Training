package application.utils.ResultGenerator;

import application.structure.StudentReport;

public interface IReportGenerator {
	
	void calculateGrade(StudentReport studentReport);
	void displayResults(StudentReport studentReport);

}
