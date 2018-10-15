package application.utils.ResultGenerator;

import java.util.Iterator;
import java.util.Map.Entry;

import application.structure.Mark;
import application.structure.StudentReport;
import application.structure.Subject;
import application.utils.Constants;

public class ReportGenerator implements IReportGenerator{

	@Override
	public void calculateGrade(StudentReport studentReport) {
		// TODO Auto-generated method stub
		Iterator<Entry<Subject, Mark>> iterator = studentReport.getSubjectMarkMap().entrySet().iterator();
		boolean status = true;
		while(iterator.hasNext()) {
			Entry<Subject, Mark> value = iterator.next();
			int internalMark = convertInternalMarks(value.getValue().getInternalMarks());
			int externalMark = convertExternalMarks(value.getValue().getExternalMarks());
			int attendance = convertAttendance(value.getValue().getAttendance());
			int totalMarks = internalMark + externalMark + attendance;
			value.getValue().setTotalMarks(totalMarks);
			if(totalMarks < 50 && status != false) {
				status = false;
				studentReport.setResultStatus(Constants.ResultStatus.fail);
			}
		}
		if(status== true) {
			studentReport.setResultStatus(Constants.ResultStatus.pass);
		}
		
	}
	
	private int convertInternalMarks(int mark) {
		return mark/4;
	}
	
	private int convertExternalMarks(int mark) {
		return (mark/4) * 3;
	}
	
	private int convertAttendance(int percent) {
		return percent/20;
	}

	@Override
	public void displayResults(StudentReport studentReport) {
		// TODO Auto-generated method stub
		Iterator<Entry<Subject, Mark>> iterator = studentReport.getSubjectMarkMap().entrySet().iterator();
		
		System.out.println(Constants.NAME + Constants.COLON + studentReport.getStudent().getName().toString());
		
		while(iterator.hasNext()) {
			Entry<Subject, Mark> value = iterator.next();
			System.out.println(Constants.SUBJECT_TITLE + Constants.COLON + value.getKey().getTitle());
			System.out.println(Constants.INTERNALS + Constants.COLON + value.getValue().getInternalMarks());
			System.out.println(Constants.EXTERNALS + Constants.COLON + value.getValue().getExternalMarks());
			System.out.println(Constants.ATTENDANCE + Constants.COLON + value.getValue().getAttendance());
			System.out.println(Constants.TOTAL_SCORE + Constants.COLON + value.getValue().getTotalMarks());
		}
		
		System.out.println(Constants.RESULT + Constants.COLON + getResult(studentReport.getResultStatus()));
		
	}
	
	private String getResult(Constants.ResultStatus resultStatus) {
		switch(resultStatus) {
			case pass:
				return Constants.PASS;
			case fail:
				return Constants.FAIL;
		}
		return null;
	}

}
