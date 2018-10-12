package application.structure;

import java.util.HashMap;
import java.util.Map;

import application.utils.Constants;

public class StudentReport {
	
	private int noOfSubjects;
	
	private Student student;
	
	private Map<Subject,Mark> subjectMarkMap = new HashMap();
	
	private Constants.ResultStatus resultStatus;
	
	
	public int getNoOfSubjects() {
		return noOfSubjects;
	}
	
	public void setNoOfSubjects(int noOfSubjects) {
		this.noOfSubjects = noOfSubjects;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Constants.ResultStatus getResultStatus() {
		return resultStatus;
	}
	
	public void setResultStatus(Constants.ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public void setSubjecMarkMap(Map<Subject,Mark> subjectMarkMap) {
		this.subjectMarkMap = subjectMarkMap;
	}
	
	public Map<Subject, Mark> getSubjectMarkMap() {
		return subjectMarkMap;
	}
	
	
}
