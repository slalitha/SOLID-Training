package com.trimble.model;

import java.util.List;

public class Student {

	private static int number = 0;

	private String mName;
	private int mRollNo;
	private List<Mark> mMarks;

	public Student(String name, List<Mark> marks) {
		this.mRollNo = number;
		this.mName = name;
		this.mMarks = marks;
		number++;
	}

	public int getmRollno() {
		return mRollNo;
	}

	public String getmName() {
		return mName;
	}

	public List<Mark> getmMarks() {
		return mMarks;
	}
}
