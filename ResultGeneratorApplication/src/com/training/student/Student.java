package com.training.student;

import com.training.constants.Constants;
import com.training.interfaces.IStudent;

public class Student implements IStudent{
	private int rollNo;
	private String name;
	private Constants.Result finalResult;

	public Student(int rollNo, String name) {
		this.rollNo = rollNo;
		this.name = name;
	}

	public int getRollNo() {
		return this.rollNo;
	}

	public String getName() {
		return this.name;
	}

	public void setFinalResult(Constants.Result finalResult) {
		this.finalResult = finalResult;
	}

	public Constants.Result getFinalResult() {
		return finalResult;
	}
}
